package org.oa_common.flie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTools {
	/**
	 * 创建文件夹
	 * @param pathString
	 */
	public static void NewFile(String pathString) {
		File file = new File(pathString);
		if (!file.exists()) {
			try {
				if (file.createNewFile()) {
					System.out.println("文件创建成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			System.out.println("文件已存在");
		}
	}

	/**
	 * 创建文件夹 删除已存在的
	 * @param pathString
	 */
	public static void NewFileBox(String pathString) {
		File file2 = new File(pathString);
		if (!file2.exists()) {
			if (file2.mkdirs()) {
				System.out.println("文件夹成功");
			}
		} else {
			System.out.println("文件夹存在");
			file2.delete();// 销毁文件
		}
	}
	
	/**
	 * 用FileWriter写入文件
	 * @param string
	 * @param fileName
	 */
	public static void ForFileWriter(String string, String fileName) {
		File file = new File(fileName);
		try {
			FileWriter fWriter = new FileWriter(file);
			fWriter.write(string);
			fWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 用BufferedWriter写入文件
	 * @param string
	 * @param desFile
	 */
	public static void ForBufferedWriter(String string, String desFile) {
		BufferedWriter bWriter = null;
		try {
			bWriter = new BufferedWriter(new FileWriter(new File(desFile)));
			bWriter.write(string.toString());
			bWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用FileReader读取文件
	 * @param fileName
	 */
	public static String testReadByReader(String fileName) {
		File file = new File(fileName);
		FileReader fis = null;
		String result;
		try {
			fis = new FileReader(file);
			char[] arr = new char[1024 * 1000 * 6];
			int len = fis.read(arr);
			String data = new String(arr, 0, len);
			fis.close();
			result = data;
		} catch (Exception e) {
			e.printStackTrace();
			result=null;
		}
		return result;
	}

	/**
	 * 用FileInputStream读取文件
	 * @param fileName
	 */
	public static String testReadByInputStream(String fileName) {
		File file = new File(fileName);
		FileInputStream fis = null;
		String result;
		try {
			fis = new FileInputStream(file);
			byte[] arr = new byte[1024 * 1000 * 6];
			int len = fis.read(arr);
			String data = new String(arr, 0, len);
			fis.close();
			result = data;
		} catch (Exception e) {
			e.printStackTrace();
			result= null;
		}
		return result;
	}

	/**
	 * 用BufferedReader读取文件
	 * @param fileName
	 */
	public static String testReadByBufferedReader(String fileName) {
		BufferedReader bReader = null;
		String line = null;
		StringBuffer buffer = new StringBuffer();
		String result;
		try {
			bReader = new BufferedReader(new FileReader(new File(fileName)));
			while ((line = bReader.readLine()) != null) {
				buffer.append(line).append("\n");
			}
			result = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	/**
	 *  字符流复制
	 * @param readfile
	 * @param writeFile
	 */
	public static void FileCopy1(String readfile, String writeFile) {
		try {
			FileReader input = new FileReader(readfile);
			FileWriter output = new FileWriter(writeFile);
			int read = input.read();
			while (read != -1) {
				output.write(read);
				read = input.read();
			}
			input.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  字节流复制
	 * @param readfile
	 * @param writeFile
	 */
	public static void FileCopy2(String readfile, String writeFile) {
		try {
			FileInputStream input = new FileInputStream(readfile);
			FileOutputStream output = new FileOutputStream(writeFile);
			int read = input.read();
			while (read != -1) {
				output.write(read);
				read = input.read();
			}
			input.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  处理流复制
	 * @param readfile
	 * @param writeFile
	 */
	public static void FileCopy3(String readfile, String writeFile) {
		BufferedReader bReader = null;
		BufferedWriter bWriter = null;
		String line = null;
		try {
			bReader = new BufferedReader(new FileReader(new File(readfile)));
			bWriter = new BufferedWriter(new FileWriter(new File(writeFile)));
			while ((line = bReader.readLine()) != null) {
				bWriter.write(line);
				bWriter.newLine();
			}
			bWriter.close();
			bReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 复制文件夹
	 * @param oldPath 
	 * @param newPath
	 * @return
	 */
	public static boolean copyFolder(String oldPath, String newPath) {
		try {
			// 如果文件夹不存在，则建立新文件夹
			(new File(newPath)).mkdirs();
			// 读取整个文件夹的内容到file字符串数组，下面设置一个游标i，不停地向下移开始读这个数组
			File filelist = new File(oldPath);
			String[] file = filelist.list();
			// 要注意，这个temp仅仅是一个临时文件指针
			// 整个程序并没有创建临时文件
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				// 如果oldPath以路径分隔符/或者\结尾，那么则oldPath/文件名就可以了
				// 否则要自己oldPath后面补个路径分隔符再加文件名
				// 谁知道你传递过来的参数是f:/a还是f:/a/啊？
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				// 如果游标遇到文件
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] bufferarray = new byte[1024 * 64];
					int prereadlength;
					while ((prereadlength = input.read(bufferarray)) != -1) {
						output.write(bufferarray, 0, prereadlength);
					}
					output.flush();
					output.close();
					input.close();
				}
				// 如果游标遇到文件夹
				if (temp.isDirectory()) {
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			return false;
		}
		return true;
	}

	/**
	 * 删除文件，可以是文件或文件夹
	 * 
	 * @param fileName
	 *            要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败:" + fileName + "不存在！");
			return false;
		} else {
			if (file.isFile()) {
				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 * 
	 * @param dir
	 *            要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			System.out.println("删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = FileTools.deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = FileTools.deleteDirectory(files[i]
						.getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			System.out.println("删除目录失败！");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			return false;
		}
	} 
}
