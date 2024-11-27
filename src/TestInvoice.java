
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;


public class TestInvoice {
    public static void main(String[] args) throws FileNotFoundException {
        
        String path = "invoice.pdf";
        
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A5);
        
        Document document = new Document(pdfDocument);
        
        document.add(new Paragraph("Hello PDF"));
        
        document.close();
    }
}
