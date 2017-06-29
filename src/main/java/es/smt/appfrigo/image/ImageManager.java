package es.smt.appfrigo.image;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.ImageSet;
import es.smt.appfrigo.blob.BlobManager;

public class ImageManager {

	private static ImageManager instance;
	
	public ImageManager(){}
	
	public static ImageManager getInstance()
	{
		if (instance == null )
		{
			instance = new ImageManager();
		}
		return instance;
	}

	  private static String getFileExtension(String fileName) {
	        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
	        return fileName.substring(fileName.lastIndexOf(".")+1);
	        else return "";
	    }		
	
	
	public ImageSet addImage(MultipartFile image, String realPath, String contextPath) throws IOException
	{
		ImageSet settings = null;
		if(image!=null)
    	{ 
			settings = new ImageSet();
//			settings.setExtension(getFileExtension(image.getOriginalFilename()));
			settings.setExtension(getFileExtension(image.getOriginalFilename()));
			settings.setContentType(image.getContentType());
			String auxName = UUID.randomUUID().toString()+ "." +settings.getExtension();
			settings.setName(UUID.randomUUID().toString()+ "." +settings.getExtension());
			settings.setLocation(realPath + "resources/out/" + settings.getName());
			settings.setAuxLocation(realPath + "resources/out/" +auxName);

			FileCopyUtils.copy(image.getBytes(), new File(settings.getAuxLocation()));

			settings.setPath(contextPath+"/container/" +auxName);
    	}
		
		return settings;
	}
	
	public String  imageDone(ImageSet settings, Image image, boolean round) throws IOException
	{
		if(settings!=null)
		{
			
			if(!settings.getExtension().equals("png"))
				ImageConverter.convertFormat(settings.getAuxLocation(), settings.getAuxLocation(), settings.getExtension(), "png");
			
			BufferedImage buffImage = ImageIO.read(new File(settings.getAuxLocation()));
			
			BufferedImage out = buffImage.getSubimage( ((Double)image.getX()).intValue(), ((Double)image.getY()).intValue(),
	    			((Double)image.getW()).intValue(), ((Double)image.getH()).intValue());
			
//			out = scale(out, 250,250);

//			String response = compress(settings);
			
//			if(response.equals("OK")){
			BufferedImage output = null;
			if(round)
			{
			
				
					output = makeRoundedCorner(out);
//					  int w = out.getWidth();
//				       int h = out.getHeight();
//				       
//				       ColorModel cm = out.getColorModel();
//				       boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
//				       WritableRaster raster = out.copyData(null);
////				       output = new  BufferedImage(cm, raster, isAlphaPremultiplied, null);
//				       
//				       output = new BufferedImage(w, h, out.getType()); // TYPE_INT_ARGB
//				//       output.setData(out.getRaster());
////				       out.getRGB(x, y)
////				       output.setRGB(x, y, rgb);
//				       Graphics2D g2 = output.createGraphics();
//				        
//
//				       g2.setComposite(AlphaComposite.Src);
//				       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
//
//				       g2.setColor(Color.BLACK);    
//				       g2.fillOval(0, 0, output.getWidth(), output.getHeight());
//				       
//				       if (image != null) {
//				    	   g2.setComposite(AlphaComposite.SrcIn); // Only paint inside the oval from now on
//				    	   g2.drawImage(out, 0, 0, null);
//				       }
				      /* g2.setBackground(Color.WHITE);    
				       g2.fill(new Ellipse2D.Double(0, 0, image.getWidth(), image.getHeight()));

				       g2.setComposite(AlphaComposite.SrcIn);
				       g2.drawImage(image, 0, 0, null);*/

//				       g2.dispose();

//				       return output;
				}
				
				File cropper = new File(settings.getLocation());
				File aux = new File(settings.getAuxLocation());
				if(round)
					ImageIO.write(output, "png", cropper);
				else ImageIO.write(out, settings.getExtension(), cropper);
				
				String state = BlobManager.getInstance().uploadProductBlob(settings.getLocation(),settings.getName(), settings.getContentType());
				cropper.delete();
				aux.delete();
				
				if(state!=null)
					return state;
//			}
		}
		return null;
	}
	
	public BufferedImage scale(BufferedImage img, int targetWidth, int targetHeight) {  //, ImageSet settings

	    //Resizing the image
	    if(img.getWidth() > 250 && img.getHeight() > 250){
	    	
	    	int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	    	BufferedImage scratchImage = null;
	   	    Graphics2D g2 = null;

	   	    int w = img.getWidth();
	   	    int h = img.getHeight();

	   	    int prevW = w;
	   	    int prevH = h;

	   	    do {
	   	        if (w > targetWidth) {
	   	            w /= 2;
	   	            w = (w < targetWidth) ? targetWidth : w;
	   	        }

	   	        if (h > targetHeight) {
	   	            h /= 2;
	   	            h = (h < targetHeight) ? targetHeight : h;
	   	        }

	   	        if (scratchImage == null) {
	   	            scratchImage = new BufferedImage(w, h, type);
	   	            g2 = scratchImage.createGraphics();
	   	        }

	   	        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	   	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	   	        g2.drawImage(img, 0, 0, w, h, 0, 0, prevW, prevH, null);

	   	        prevW = w;
	   	        prevH = h;
	   	        img = scratchImage;
	   	    } while (w != targetWidth || h != targetHeight);

	   	    if (g2 != null) {
	   	        g2.dispose();
	   	    }

	   	    if (targetWidth != img.getWidth() || targetHeight != img.getHeight()) {
	   	        scratchImage = new BufferedImage(targetWidth, targetHeight, type);
	   	        g2 = scratchImage.createGraphics();
	   	        g2.drawImage(img, 0, 0, null);
	   	        g2.dispose();
	   	        img = scratchImage;
	   	    }
	    }
	    
	    return img;
	}

	
	@SuppressWarnings("resource")
	public String compress(ImageSet settings) throws IOException{
		
		File imageFile = new File(settings.getAuxLocation());
		File compressedImageFile = new File(settings.getLocation());

		InputStream inputStream = new FileInputStream(imageFile);
		OutputStream outputStream = new FileOutputStream(compressedImageFile);

		float imageQuality = 0.4f;

		//Create the buffered image
		BufferedImage bufferedImage = ImageIO.read(inputStream);

		//Get image writers
		Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("jpg");

		if (!imageWriters.hasNext())
			throw new IllegalStateException("Writers Not Found!!");

		ImageWriter imageWriter = (ImageWriter) imageWriters.next();
		ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
		imageWriter.setOutput(imageOutputStream);

		ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

		//Set the compress quality metrics
		imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		imageWriteParam.setCompressionQuality(imageQuality);

		//Created image
		imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);

		// close all streams
		inputStream.close();
		outputStream.close();
		imageOutputStream.close();
		imageWriter.dispose();
		
		return "OK";
	}
	
	public static BufferedImage makeRoundedCorner(BufferedImage image) {
       int w = image.getWidth();
       int h = image.getHeight();
       BufferedImage output = new BufferedImage(w, h,BufferedImage.TYPE_INT_ARGB);  // TYPE_INT_ARGB
      
       Graphics2D g2 = output.createGraphics();
           

     //  g2.setComposite(AlphaComposite.Src);
//       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
//
//       g2.setColor(Color.YELLOW);
//       g2.setBackground(Color.YELLOW); 
       g2.fillOval(0, 0, output.getWidth(), output.getHeight());
       
       if (image != null) {
    	   g2.setComposite(AlphaComposite.SrcIn); // Only paint inside the oval from now on
    	   g2.drawImage(image, 0, 0, null);
       }
      /* g2.setBackground(Color.WHITE);    
       g2.fill(new Ellipse2D.Double(0, 0, image.getWidth(), image.getHeight()));

       g2.setComposite(AlphaComposite.SrcIn);
       g2.drawImage(image, 0, 0, null);*/

       g2.dispose();

       return output;
   }
}
