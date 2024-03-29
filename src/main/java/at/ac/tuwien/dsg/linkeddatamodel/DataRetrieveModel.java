/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.linkeddatamodel;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;


/**
 *
 * @author Anindita
 */
public class DataRetrieveModel {
   public String monitoredObjectName(String sensorName, String buildingName)
   {
       
       
       String objectName="";
       String uri="http://somewhere/index#";
       
       Model model=FileManager.get().loadModel("./example/"+buildingName+".rdf");
       
       
       
       Resource property=model.getResource(uri+buildingName);
       Property buildingNameProperty=model.getProperty(uri+"BuildingName");
       
       String buildingname=property.getProperty(buildingNameProperty).getString();
       System.out.println("hellooooo: "+buildingname);
       
       Property sensorProperty=model.getProperty(uri+"SensorInformation");
       Property objectNameProperty=model.getProperty(uri+"MonitoredObjectName");
       Property sensorNameProperty=model.getProperty(uri+"SensorName");
       
       
       
      StmtIterator iter=property.listProperties(sensorProperty);
       while (iter.hasNext()) {
           Statement st=iter.nextStatement();
           Resource observation=(Resource)st.getObject();
           
           String name=observation.getProperty(sensorNameProperty).getObject().toString();
           if(name.equals(sensorName))
           {
               objectName=observation.getProperty(objectNameProperty).getObject().toString();
           }
           else
           {
               System.out.println("The Object is OK ");
           }
                                   
}
       System.out.println("object has some problem :"+objectName);

       
       
       
       return objectName;
       
       
       
       
       
   }
}
