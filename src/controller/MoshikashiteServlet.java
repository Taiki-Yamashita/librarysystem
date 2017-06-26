//package controller;
//
//import java.io.IOException;
//
//import org.apache.tomcat.jni.Directory;
//
//import com.mysql.jdbc.Field;
//import com.sun.xml.internal.txw2.Document;
//
//public class MoshikashiteServlet {
//
//	static final String content = "ここに「施政方針演説」のテキストを挿入";
//    static final String[] queries = { "内閣", "内職", "相撲", "相模" };
//    static final String F = "F";
//
//
//    static Directory directory = new RAMDirectory();
//
//    static Analyzer analyzer = new JapaneseAnalyzer();
//
//
//
//    public static void main( String[] args ) throws IOException {
//
//        makeIndex();
//
//        for( String query : queries ){
//
//            int length = searchIndex( query );
//
//            if( length > 0 )
//
//                System.out.println( "¥n["  query  "] : " 
//
//                                    Integer.toString( length ) 
//
//                                    " 件ヒットしました。" );
//
//            else
//
//                printPossibilities( query );
//
//        }
//
//    }
//
//
//
//    static void makeIndex() throws IOException {
//
//        IndexWriter writer = new IndexWriter( directory, analyzer, true );
//
//        Document doc = new Document();
//
//        doc.add( new Field( F, content, Field.Store.NO, Field.Index.TOKENIZED ) );
//
//        writer.addDocument( doc );
//
//        writer.close();
//
//    }
//
//
//
//    static int searchIndex( String q ) throws IOException {
//
//        IndexSearcher searcher = new IndexSearcher( directory );
//
//        Hits hits = searcher.search( new TermQuery( new Term( F, q ) ) );
//
//        int length = hits.length();
//
//        searcher.close();
//
//        return length;
//
//    }
//
//
//
//    static void printPossibilities( String q ) throws IOException {
//
//        IndexReader reader = IndexReader.open( directory );
//
//        FuzzyQuery fq = new FuzzyQuery( new Term( F, q ), 0.4F );
//
//        BooleanQuery bq = (BooleanQuery)fq.rewrite( reader );
//
//        BooleanClause[] clauses = bq.getClauses();
//
//        if( clauses.length > 0 ){
//
//            System.out.println( "¥n["  q  "] もしかして・・・" );
//
//            for( BooleanClause clause : clauses ){
//
//                Term term = ((TermQuery)clause.getQuery()).getTerm();
//
//                System.out.println( "¥t"  term.text() );
//
//            }
//
//        }
//
//        reader.close();
//
//    }
//}
