package org.oa_common.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import org.apache.commons.io.FileUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 常用的图片压缩工具类
 * @author Administrator
 *
 */
public class ImageTools {
	/**  
     * 对图片裁剪，并把裁剪新图片保存  
     * @param srcPath 读取原图片路径 
     * @param toPath    写入图片路径 
     * @param x 剪切起始点x坐标 
     * @param y 剪切起始点y坐标 
     * @param width 剪切宽度 
     * @param height     剪切高度 
     * @param readImageFormat  读取图片的格式 
     * @param writeImageFormat 写入图片的格式 
     * @throws IOException 
     */  
    public static void cropImage(String srcPath,String toPath,int x,int y,int width,int height,  
            String readImageFormat,String writeImageFormat) throws IOException{     
        FileInputStream fis = null ;  
        ImageInputStream iis =null ;  
        try{     
            //读取图片文件  
            fis = new FileInputStream(srcPath);   
            //获取图片的格式
            Iterator it = ImageIO.getImageReadersByFormatName(readImageFormat);
            ImageReader reader = (ImageReader) it.next();   
            //获取图片流   
            iis = ImageIO.createImageInputStream(fis);    
            reader.setInput(iis,true) ;  
            ImageReadParam param = reader.getDefaultReadParam();   
            //定义一个矩形  (通过 Rectangle 对象 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。)
            Rectangle rect = new Rectangle(x, y, width, height);   
            //提供一个 BufferedImage，将其用作解码像素数据的目标。   
            param.setSourceRegion(rect);  
            BufferedImage bi = reader.read(0,param);                  
            //保存新图片   
            ImageIO.write(bi, writeImageFormat, new File(toPath));       
        }finally{  
            if(fis!=null)  
                fis.close();         
            if(iis!=null)  
               iis.close();   
        }   
    }  
    /** 
     * 按倍率缩小图片 
     * @param srcImagePath 读取图片路径 
     * @param toImagePath 写入图片路径 
     * @param widthRatio    宽度缩小比例 
     * @param heightRatio    高度缩小比例 
     * @throws IOException 
     */  
    public static void reduceImageByRatio(String srcImagePath,String toImagePath,int widthRatio,int heightRatio) throws IOException{  
        FileOutputStream out = null;  
        try{  
            //读入文件    
            File file = new File(srcImagePath);    
            // 构造Image对象    
            BufferedImage src = javax.imageio.ImageIO.read(file);    
            int width = src.getWidth();    
            int height = src.getHeight();    
            // 缩小边长   
            BufferedImage tag = new BufferedImage(width / widthRatio, height / heightRatio, BufferedImage.TYPE_INT_RGB);    
            // 绘制 缩小  后的图片   
            tag.getGraphics().drawImage(src, 0, 0, width / widthRatio, height / heightRatio, null);    
            out = new FileOutputStream(toImagePath);    
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);    
            encoder.encode(tag);    
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            if(out != null){  
                out.close();    
            }  
        }  
    }  
	 /** 
     * 长高等比例缩小图片 
     * @param srcImagePath 读取图片路径 
     * @param toImagePath 写入图片路径 
     * @param ratio 缩小比例 
     * @throws IOException 
     */ 
    public static void reduceImageEqualProportion(String srcImagePath,String toImagePath,int ratio) throws IOException{  
        FileOutputStream out = null;  
        try{  
            //读入文件    
            File file = new File(srcImagePath);    
            // 构造Image对象    
            BufferedImage src =ImageIO.read(file);    
            int width = src.getWidth();    
            int height = src.getHeight();    
            // 缩小边长   
            BufferedImage tag = new BufferedImage(width / ratio, height / ratio, BufferedImage.TYPE_INT_RGB);    
            // 绘制 缩小  后的图片   
            tag.getGraphics().drawImage(src, 0, 0, width / ratio, height / ratio, null);    
            out = new FileOutputStream(toImagePath);    
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);    
            encoder.encode(tag);    
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            if(out != null){  
                out.close();    
            }  
        }  
    }  
    
    /** 
     * 重置图形的边长大小 
     * @param srcImagePath 读取图片路径  
     * @param toImagePath 写入图片路径 
     * @param width 重置的图片长度
     * @param height 重置的图片高度
     * @throws IOException 
     */  
    public static void resizeImage(String srcImagePath,String toImagePath,int width,int height) throws IOException{  
        FileOutputStream out = null;  
        try{  
            //读入文件    
            File file = new File(srcImagePath);    
            // 构造Image对象    
            BufferedImage src = javax.imageio.ImageIO.read(file);    
            // 放大边长  
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);    
            //绘制放大后的图片  
            tag.getGraphics().drawImage(src, 0, 0, width, height, null);    
            out = new FileOutputStream(toImagePath);    
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);    
            encoder.encode(tag);    
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            if(out != null){  
                out.close();    
            }  
        }  
    }  
    /** 
     * 图片灰化操作 
     * @param srcImage 读取图片路径 
     * @param toPath    写入灰化后的图片路径 
     * @param imageFormat 图片写入格式
     * ColorSpace作用：用做一个颜色空间标记，标识 Color 对象的特定颜色空间。  
     * ColorConvertOp:颜色转换操作类
     */   
    public static void grayImage(String srcImage,String toPath,String imageFormat){  
        try{  
            BufferedImage src = ImageIO.read(new File(srcImage));  
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);  
            ColorConvertOp op = new ColorConvertOp(cs, null);  
            src = op.filter(src, null);  
            ImageIO.write(src, imageFormat, new File(toPath));  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }   
    
    /** 
     * 在源图片上设置文字水印 
     * @param srcImagePath  原图片路径 
     * @param alpha 透明度（0<alpha<1） 
     * @param font  字体（例如：宋体） 
     * @param fontStyle     字体格式(例如：普通样式--Font.PLAIN、粗体--Font.BOLD ) 
     * @param fontSize  字体大小 
     * @param color 字体颜色(例如：黑色--Color.BLACK) 
     * @param inputWords        输入显示在图片上的文字 
     * @param x     文字显示起始的x坐标 
     * @param y     文字显示起始的y坐标 
     * @param imageFormat   写入图片格式（png/jpg等） 
     * @param toPath    写入图片路径 
     * @throws IOException  
     */  
    public void alphaWords2Image(String srcImagePath,float alpha,String font,int fontStyle,int fontSize,Color color,  
            String inputWords,int x,int y,String imageFormat,String toPath) throws IOException{  
        FileOutputStream fos=null;  
        try {  
            BufferedImage image = ImageIO.read(new File(srcImagePath));  
            //创建java2D对象  
            Graphics2D g2d=image.createGraphics();  
            //用原图像填充背景  
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null, null);  
            //设置透明度  
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);  
            g2d.setComposite(ac);  
            //设置文字字体名称、样式、大小  
            g2d.setFont(new Font(font, fontStyle, fontSize));  
            g2d.setColor(color);//设置字体颜色  
            g2d.drawString(inputWords, x, y); //输入水印文字及其起始x、y坐标  
            g2d.dispose();  
            fos=new FileOutputStream(toPath);  
            ImageIO.write(image, imageFormat, fos);  
        } catch (Exception e) {  
           e.printStackTrace();  
        }finally{  
            if(fos!=null){  
                fos.close();  
            }  
        }  
    }  
    /**
     * 图片像素质量压缩
     * @param originalFile 读取图片文件
     * @param resizedFile 输出图片文件路径
     * @param newWidth 新的宽度
     * @param quality 图片质量参数 0.7f 相当于70%质量  
     * @throws IOException
     */
    public static void resize(File originalFile, File resizedFile,    
            int newWidth, float quality) throws IOException {    
     
        if (quality > 1) {    
            throw new IllegalArgumentException(    
                    "分辨率必须在0~1之间");    
        }    
     
        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());    
        Image i = ii.getImage();    
        Image resizedImage = null;    
     
        int iWidth = i.getWidth(null);    
        int iHeight = i.getHeight(null);    
     
        if(iWidth < newWidth){  
            newWidth = iWidth;  
        }  
        if (iWidth > iHeight) {    
            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)    
                    / iWidth, Image.SCALE_SMOOTH);    
        } else {    
            resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,    
                    newWidth, Image.SCALE_SMOOTH);    
        }    
     
        // This code ensures that all the pixels in the image are loaded.    
        Image temp = new ImageIcon(resizedImage).getImage();    
     
        // Create the buffered image.    
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),    
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);    
     
        // Copy image to buffered image.    
        Graphics g = bufferedImage.createGraphics();    
     
        // Clear background and paint the image.    
        g.setColor(Color.white);    
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));    
        g.drawImage(temp, 0, 0, null);    
        g.dispose();    
     
        // Soften.    
        float softenFactor = 0.05f;    
        float[] softenArray = { 0, softenFactor, 0, softenFactor,    
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };    
        Kernel kernel = new Kernel(3, 3, softenArray);    
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);    
        bufferedImage = cOp.filter(bufferedImage, null);    
     
        // Write the jpeg to a file.    
        FileOutputStream out = new FileOutputStream(resizedFile);    
     
        // Encodes image as a JPEG data stream    
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);    
     
        JPEGEncodeParam param = encoder    
                .getDefaultJPEGEncodeParam(bufferedImage);    
     
        param.setQuality(quality, true);    
     
        encoder.setJPEGEncodeParam(param);    
        encoder.encode(bufferedImage);    
    } 
    /**
     * 压缩图片处理压缩后的分辨率
     * @param imgsrc 要压缩的文件路径
     * @param imgdist 压缩后的文件路径
     * @param widthdist 压缩图片的宽度
     * @param heightdist 压缩图片的高度
     */
    public static void reduceImg(String imgsrc, String imgdist, int widthdist,        
            int heightdist) {        
        try {        
            File srcfile = new File(imgsrc);        
            if (!srcfile.exists()) {        
                return;        
            }        
            Image src = javax.imageio.ImageIO.read(srcfile);        
           
            BufferedImage tag= new BufferedImage((int) widthdist, (int) heightdist,        
                    BufferedImage.TYPE_INT_RGB);        
           
            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_SMOOTH), 0, 0,  null);        
//            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_AREA_AVERAGING), 0, 0,  null);        
                    
            FileOutputStream out = new FileOutputStream(imgdist);        
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);        
            encoder.encode(tag);        
            out.close();        
           
        } catch (IOException ex) {        
            ex.printStackTrace();        
        }        
    }     
    
    
    public static void main(String[] args) throws FileNotFoundException {
    	//文件物理路径
		String srcImage="D:/EWorkspace/OaYibang/src/main/webapp/uploadfolder/20160930/";
		File fileSrc=new File(srcImage);
		//文件复制的物理路径
		final String copy_image="D:/EWorkspace/OaYibang/src/main/webapp/download";
		File folder = new File(copy_image);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File fileTo=new File(copy_image);
		try {
			FileUtils.copyDirectory(fileSrc,fileTo);
			String toImagePath=copy_image+"/"+"乡村人家.jpg";
			File file = new File(toImagePath);
			FileInputStream fis = new FileInputStream(file);
			BufferedImage bufferedImg = ImageIO.read(fis);
			int imgWidth = bufferedImg.getWidth();//图片宽度
			int imgHeight = bufferedImg.getHeight();//图片高度
			System.out.println(imgWidth+"@@@@@@@@@@@@@"+imgWidth/10);
			System.out.println(imgHeight+"=========="+imgHeight/10);
//			File file=new File(toImagePath);
//			File file1=new File("D:/EWorkspace/OaYibang/src/main/webapp/download/ys_"+"1475208115844.jpg");
//			CompressImage.reduceImageEqualProportion(toImagePath, "D:/EWorkspace/OaYibang/src/main/webapp/download/ys_"+"1475208115844.jpg", 5);
//			CompressImage.resize(file, file1, 50, 0.8f);
			ImageTools.reduceImg(toImagePath, "D:/EWorkspace/OaYibang/src/main/webapp/download/ys_"+"乡村人家.jpg", imgWidth/10, imgHeight/10);
			System.out.println("压缩成功!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
