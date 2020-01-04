package com;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AppService {

	List<Record> toJavaObject(File serverFile) {
		
	    
		//XML elements names as final String literals
        final String RECORD = "record";
        final String REFERENCE = "reference";
        final String ACCOUNTNUMBER = "accountNumber";
        final String DESCRIPTION = "description";
        final String STARTBALANCE = "startBalance";
        final String MUTATION = "mutation";
        final String ENDBALANCE = "endBalance";

        
        
        List<Record> recordsList = new ArrayList<>();
        
		try {
			
			SAXBuilder saxbuild = new SAXBuilder();
			Document doc = saxbuild.build(serverFile);

			System.out.println("doc: "+doc.toString());

			//getting rootElement
			Element elements=doc.getRootElement();
			System.out.println("Rootelement: "+doc.getRootElement().getName());
			
			if(doc.getRootElement().getName().equals("records")){
				List<Element> recordsXml=elements.getChildren();
				
				for(Element recordElement: recordsXml){
					Record myRecord= new Record();
					myRecord.setReference(recordElement.getAttribute(REFERENCE).getValue());
					myRecord.setAccountNumber(recordElement.getChild(ACCOUNTNUMBER).getValue());
					myRecord.setDescription(recordElement.getChild(DESCRIPTION).getValue());
					
					double startbalance=Double.parseDouble(recordElement.getChild(STARTBALANCE).getValue());
					myRecord.setStartBalance(startbalance);
					
					double mutation=Double.parseDouble(recordElement.getChild(MUTATION).getValue());
					myRecord.setMutation(mutation);
					
					double endBalance=Double.parseDouble(recordElement.getChild(ENDBALANCE).getValue());
					myRecord.setEndBalance(endBalance);
					
					//converting xmlelements as java object and adding to the list
					recordsList.add(myRecord);
				}
			}
			else{
				System.out.println("Invalid xml..");
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordsList;
	}

	public List<Record> validate(List<Record> flistOfRecords) {
		List<Record> failedrecords=new ArrayList<>();
		Set<Record> failedreference=new HashSet<>();
		HashMap<String, Record> tocheck= new HashMap<>();
		
		
		try{
			for(Record currentRecord:flistOfRecords)
			{

				DecimalFormat df=new DecimalFormat("0.00");
				double vendbal=currentRecord.getStartBalance()+currentRecord.getMutation();
				vendbal=Double.parseDouble(df.format(vendbal));

				//validating end balance
				if(currentRecord.getEndBalance()!=vendbal || currentRecord.getEndBalance()<0){
					failedrecords.add(currentRecord);
				}
				
				//validating transcation reference
				if(tocheck.containsKey(currentRecord.getReference())){
					failedreference.add(currentRecord);
				}else{
					tocheck.put(currentRecord.getReference(), currentRecord);
				}
				
			}
			
			failedrecords.addAll(failedreference);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return failedrecords;
	}
}
