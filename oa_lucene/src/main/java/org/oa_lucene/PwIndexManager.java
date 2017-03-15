package org.oa_lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年11月24日 上午8:40:29 
 * @version 1.0 
 */
public class PwIndexManager {
    private  Analyzer analyzer = null;
    private  Directory directory = null;
    private  IndexWriter indexWriter = null;
    
    /**
     * 创建文章索引
     * @param indexPath 索引地址
     * @param pwId 文章id
     * @param content 文章内容
     * @param userId 发布者id
     * @param orgId  所属机构id
     * @return
     */
    public boolean createIndex(String indexPath,String pwId,String content,String userId,String orgId){
            try{
                analyzer = new PaodingAnalyzer(); 
                directory = FSDirectory.open(new File(indexPath));
                File indexFile = new File(indexPath);
                if (!indexFile.exists()) {
                    indexFile.mkdirs();
                }
                IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, analyzer);
                indexWriter = new IndexWriter(directory, config);
                Document document = new Document();
                document.add(new Field("pwId", pwId, Field.Store.YES,Field.Index.NOT_ANALYZED));
                document.add(new Field("content", content, Store.YES,Field.Index.ANALYZED));
                document.add(new Field("userId", userId, Store.YES,Field.Index.NOT_ANALYZED));
                document.add(new Field("orgId", orgId, Store.YES,Field.Index.NOT_ANALYZED));
                indexWriter.addDocument(document);
                indexWriter.commit();
                closeWriter();
            }catch(Exception e){
                e.printStackTrace();
            }
         return true;
    }
    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public String txt2String(File file){
    	String line="";   
    	StringBuffer result =new StringBuffer("");
    	try{
    	 StringBuffer strBuffer=new StringBuffer();   
         FileInputStream is=new FileInputStream(file.getCanonicalPath());   
         BufferedReader reader=new BufferedReader(new InputStreamReader(is,"GBK"));   
         line=reader.readLine();   
         while(line != null){   
           result.append(line);
           strBuffer.append(line);   
           strBuffer.append("\n");
           line=reader.readLine();
         }   
    	}catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
    /**
     * 读取doc文件内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     
    public  String doc2String(File file){
        String result = "";
        try{
            FileInputStream fis = new FileInputStream(file);
            HWPFDocument doc = new HWPFDocument(fis);
            Range rang = doc.getRange();
            result += rang.text();
            fis.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    */
    /**
     * 读取xls文件内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     
    public  String xls2String(File file){
        String result = "";
        try{
            FileInputStream fis = new FileInputStream(file);   
            StringBuilder sb = new StringBuilder();   
            jxl.Workbook rwb = Workbook.getWorkbook(fis);   
            Sheet[] sheet = rwb.getSheets();   
            for (int i = 0; i < sheet.length; i++) {   
                Sheet rs = rwb.getSheet(i);   
                for (int j = 0; j < rs.getRows(); j++) {   
                   Cell[] cells = rs.getRow(j);   
                   for(int k=0;k<cells.length;k++)   
                   sb.append(cells[k].getContents());   
                }   
            }   
            fis.close();   
            result += sb.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }*/
    /**
     * 查找索引，返回符合条件的文件
     * @param text 查找的字符串
     * @return 符合条件的文件List
     */
    public String searchIndex(String indexPath,String text,String userId,String orgIds){
        try{
            analyzer = new PaodingAnalyzer();
            StringReader reader = new StringReader(text); 
            TokenStream ts = analyzer.tokenStream(text, reader); 
            boolean hasnext = ts.incrementToken(); 
            while (hasnext) {  
                CharTermAttribute ta = ts.getAttribute(CharTermAttribute.class);  
                hasnext = ts.incrementToken();  
            }  
            ts.close();  
            directory = FSDirectory.open(new File(indexPath));
            IndexReader ireader = IndexReader.open(directory);
            IndexSearcher isearcher = new IndexSearcher(ireader);
            //isearcher.searchAfter(after, query, n)
            BooleanQuery booleanQuery = new BooleanQuery();
            
            QueryParser parser = new QueryParser(Version.LUCENE_36, "content", analyzer);
            Query query = parser.parse(text);
            booleanQuery.add(query, Occur.MUST);
            
            if(userId != null && !"".equals(userId)){
            	 QueryParser parseruserId = new QueryParser(Version.LUCENE_36, "userId", analyzer);
                 Query queryuserId = parseruserId.parse(userId);
                 booleanQuery.add(queryuserId, Occur.MUST);
            }
            
            if(orgIds != null && !"".equals(orgIds)){
            	String []ids = orgIds.split(",");
            	for(int i=0;i<ids.length;i++){
            		QueryParser parseruserId = new QueryParser(Version.LUCENE_36, "orgId", analyzer);
                    Query queryuserId = parseruserId.parse(ids[i]);
                    booleanQuery.add(queryuserId, Occur.SHOULD);
            	}
            }
            TopDocs docs = isearcher.search(booleanQuery, 1000);  
            ScoreDoc[] hits = docs.scoreDocs;
            StringBuffer pwids = new StringBuffer("");
            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = isearcher.doc(hits[i].doc);
                pwids.append(hitDoc.get("pwId")+",");
            }
            pwids = pwids.deleteCharAt(pwids.length()-1);
            ireader.close();
            directory.close();
            return pwids.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 过滤目录下的文件
     * @param dirPath 想要获取文件的目录
     * @return 返回文件list
     */
    public  List<File> getFileList(String dirPath) {
        File[] files = new File(dirPath).listFiles();
        List<File> fileList = new ArrayList<File>();
        for (File file : files) {
            if (isTxtFile(file.getName())) {
                fileList.add(file);
            }
        }
        return fileList;
    }
    /**
     * 判断是否为目标文件，目前支持txt xls doc格式
     * @param fileName 文件名称
     * @return 如果是文件类型满足过滤条件，返回true；否则返回false
     */
    public  boolean isTxtFile(String fileName) {
        if (fileName.lastIndexOf(".txt") > 0) {
            return true;
        }else if (fileName.lastIndexOf(".xls") > 0) {
            return true;
        }else if (fileName.lastIndexOf(".doc") > 0) {
            return true;
        }
        return false;
    }
    
    public  void closeWriter() throws Exception {
        if (indexWriter != null) {
            indexWriter.close();
        }
    }
    /**
     * 删除文件目录下的所有文件
     * @param file 要删除的文件目录
     * @return 如果成功，返回true.
     */
    public  boolean deleteDir(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(int i=0; i<files.length; i++){
                deleteDir(files[i]);
            }
        }
        file.delete();
        return true;
    }
}
