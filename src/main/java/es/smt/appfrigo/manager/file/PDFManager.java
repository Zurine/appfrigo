package es.smt.appfrigo.manager.file;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import es.smt.appfrigo.bean.Data;
import es.smt.appfrigo.bean.PDFFile;


public class PDFManager {

	private static PDFManager instance;
	
	private static String folder = "resources/out/";
	
	public PDFManager(){}
	
	public static PDFManager getInstance()
	{
		if (instance == null )
		{
			instance = new PDFManager();
		}
		return instance;
	}
	
	
	public String generatePDFFile(PDFFile file) throws IOException, COSVisitorException
	{
		String url = file.getPath()+folder+file.getName()+".pdf";
		// Create a document and add a page to it
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage( page );

		// Create a new font object selecting one of the PDF base fonts
		PDFont font = PDType1Font.HELVETICA_BOLD;

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream contentStream = new PDPageContentStream(document, page);//,true,true

		int fontSize = 14; 
		//String title = "Sales Statistics";
		float titleWidth = font.getStringWidth(file.getTitle()) / 1000 * fontSize;
		float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
		int marginTop = 30; 
		
		// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
		contentStream.beginText();
		contentStream.setFont(font, fontSize);
	//	contentStream.moveTextPositionByAmount( 50, 720 );
		contentStream.moveTextPositionByAmount((page.getMediaBox().getWidth() - titleWidth) / 2, page.getMediaBox().getHeight() - marginTop - titleHeight);
		contentStream.drawString(file.getTitle());
		contentStream.endText();

		drawTable(document, page, contentStream, 680, 50, file);
		

		// Make sure that the content stream is closed:
		contentStream.close();

		// Save the results and ensure that the document is properly closed:
		document.save(url);
		document.close();
		url = file.getName()+".pdf";
		
		return url;
	
	}
	
	
	private void drawTable(PDDocument document , PDPage page, PDPageContentStream contentStream,  float y, float margin,PDFFile file) throws IOException {
		
		final float rowHeight = 16f;
		final float tableWidth = page.findMediaBox().getWidth()-(2*margin);
//		final float firstCol = 75;
		final float cellMargin=4f;
		int maxSize = file.getMaxSize();
		if(maxSize > 156)
			maxSize = 156;
		//maxSize += 180*2.2;
		float size = (float) (maxSize + (185*2.3));
		//Pintamos los datos de la busqueda
		contentStream.setFont(PDType1Font.HELVETICA_BOLD,9);
		
		
		//Las fechas
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(margin+4,y+18);
		if(file.getStartDate()!=null && file.getEndDate()!=null )
			contentStream.drawString(file.getStartDate() + " to " + file.getEndDate());
		contentStream.endText();
		
		//Ponemos todo en un recuadro
		//Lines horizontales
		float lineH = file.getFilter().size()*17;
		contentStream.drawLine(margin,y+14,size,y+14);
		contentStream.drawLine(margin,y+14-lineH,size,y+14+-lineH);
		//Lines verticales
		contentStream.drawLine(margin,y+14-lineH,margin,y+14);
		contentStream.drawLine(size,y+14-lineH,size,y+14);
		
		//Añadimos tamaño a la y
		
		float tx = margin+cellMargin;
		

		
		for(Data d:file.getFilter())
		{
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(tx,y+2);
			System.out.println("The length---> " + d.getData().length());
			if(d.getData().length()>100)
			{
				String dd = d.getData().substring(0, 100);
				contentStream.drawString(d.getLabel() + " " + dd+"...");
			}
			else contentStream.drawString(d.getLabel() + " " + d.getData());

			contentStream.endText();
			y-=rowHeight;
		}
		
		//variables
		int rows = 0;
		int cols =0;
		float tableHeight = 0;
	//	float colWidth = 0;
		float nexty = 0;
		float textx = 0;
		float texty = 0;
		float nextx = 0;
		int currentPage = 1;
		//Vamos pintado por hojas
		boolean start = true;
		for(List<List<String>> table : file.getTable())
		{
			if(!start)
			{
				y = 745;
				page = new PDPage();
				document.addPage(page);
				contentStream = new PDPageContentStream(document,page,true,true);
			}
			start = false;
			
			rows = table.size()+1;
			cols = table.get(0).size();
			tableHeight = rowHeight * rows;
		//	colWidth = (tableWidth-firstCol)/((float)cols-1);
			//draw the rows
			nexty = y;
			for (int i = 0; i <= rows; i++) {
				contentStream.drawLine(margin,nexty,margin+tableWidth,nexty);
				nexty-= rowHeight;
			}
			
			textx = margin+cellMargin;
			texty = y-15;
			
			//Add Color to header
			contentStream.setNonStrokingColor(216, 216, 216); //gray background
			contentStream.fillRect(margin, texty, tableWidth, 14);
			contentStream.setNonStrokingColor( Color.black );
			

			//draw the columns
			nextx = margin;
			for (int i = 0; i <= cols; i++) {
				contentStream.drawLine(nextx,y,nextx,y-tableHeight);
			//	if(i == 0)
			//		nextx += firstCol;
			//	else 
				if(i<cols)
					nextx += file.getCols().get(i)*tableWidth;
			}
			
			//now add the text
			contentStream.setFont(PDType1Font.HELVETICA_BOLD,8);

			
			//Pintamos la cabecera
			int pos = 0;
			for(String s:file.getHeader())
			{
				String text = s;
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx,texty+2);
				contentStream.drawString(text);

				contentStream.endText();
			//	if(textx == margin+cellMargin)
			//		textx += firstCol;
				//else textx += colWidth;
				textx += file.getCols().get(pos)*tableWidth;
				pos++;
				
			}
			texty-=rowHeight;
			textx = margin+cellMargin;
			
			//table.remove(0);
			contentStream.setFont(PDType1Font.HELVETICA_BOLD,8);
		
			for(List<String> t : table)
			{
				pos = 0;
				for(String s:t)
				{
					String text = s;
					contentStream.beginText();
					contentStream.moveTextPositionByAmount(textx,texty+1);
					contentStream.drawString(text);
					contentStream.endText();
					//textx += colWidth;
				//	if(textx == margin+cellMargin)
				//		textx += firstCol;
			//		else textx += colWidth;
					textx += file.getCols().get(pos)*tableWidth;
					pos++;
						
				}
				texty-=rowHeight;
				textx = margin+cellMargin;
			}
			
			 // PDPageContentStream footercontentStream = new PDPageContentStream(doc, page, true, true);
			//Footer
			contentStream.beginText();
	        //    footercontentStream.setFont(font, fontSize);
			contentStream.moveTextPositionByAmount((PDPage.PAGE_SIZE_A4.getUpperRightX() / 2), (PDPage.PAGE_SIZE_A4.getLowerLeftY()+20));
			contentStream.drawString(String.valueOf(currentPage));
			contentStream.endText();
			
			contentStream.close();
			currentPage++;

		}


	}
}
