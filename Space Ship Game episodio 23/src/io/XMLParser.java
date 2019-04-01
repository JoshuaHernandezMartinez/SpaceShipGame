package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import gameObjects.Constants;

public class XMLParser {
	
	public static ArrayList<ScoreData> readFile() {
		
		ArrayList<ScoreData> dataList = new ArrayList<ScoreData>();
		
		try {
			
			File file = new File(Constants.SCORE_PATH);
			
			if(!file.exists() || file.length() == 0) {
				return dataList;
			}
			
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(new FileInputStream(file));
			ScoreData data = null;
			
			while(eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if(event.isStartElement()) {
					StartElement start = event.asStartElement();
					
					if(start.getName().getLocalPart().equals(Constants.PLAYER)) {
						data = new ScoreData();
					}else if(start.getName().getLocalPart().equals(Constants.DATE)) {
						event = eventReader.nextEvent();
						data.setDate(event.asCharacters().getData());
					}else if(start.getName().getLocalPart().equals(Constants.SCORE)) {
						event = eventReader.nextEvent();
						data.setScore(Integer.parseInt(event.asCharacters().getData()));
					}
				}
				
				if(event.isEndElement()) {
					EndElement end = event.asEndElement();
					if(end.getName().getLocalPart().equals(Constants.PLAYER)) {
						dataList.add(data);
					}
				}
			}
			return dataList;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void writeFile(ArrayList<ScoreData> dataList) throws XMLStreamException, IOException {
		
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		
		File outputFile = new File(Constants.SCORE_PATH);
		
		outputFile.getParentFile().mkdir();
		
		outputFile.createNewFile();
		
	    XMLStreamWriter writer = outputFactory.createXMLStreamWriter(
	             new FileOutputStream(outputFile), "UTF-8");
		
	    
	    writer.writeStartDocument();
	    writer.writeStartElement(Constants.PLAYERS);
	     
		for(ScoreData data: dataList) {
		     writer.writeStartElement(Constants.PLAYER);
		     writer.writeStartElement(Constants.DATE);
		     writer.writeCharacters(data.getDate());
		     writer.writeEndElement();
		     writer.writeStartElement(Constants.SCORE);
		     writer.writeCharacters(Integer.toString(data.getScore()));
		     writer.writeEndElement();
		     writer.writeEndElement();
		}
		
		writer.writeEndElement();
	    writer.flush();
	    writer.close();
		
	}
	
}
