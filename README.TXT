University
==========

This repo contains projects created for university courses

AIC - Advanced Internet Computing (AIC_2010_WS)
==========================================================================================
Assignment 0 deals with converting a web service description (WSDL) gathered via a link and creating Java objects to invoke the service which registered the caller for the lecture.
Assignment 1 creates a couple of SOAP based web services to implement an online book seller with 2 suppliers and a central warehouse which is first looked up before ordering a product from a supplier.
Assignment 2 uses the web service - business process engineering languge (WS-BPEL) to orchestrate the web services created in the previous assignment. Further a REST service with typical CRUD feature is created which gets invoked by a SOAP service. 

DST - Distributed System Technologies (DST_2011_SS)
==========================================================================================
Assignment 1 creates the basic infrastructure for a GridManagement system. It uses JPA and Hibernate as well as self-created Annotations to setup a model which will be used later in a JavaEE 6 environment. A couple of queries and transactions are sent to an EntityManager to test the functionality of the created model
Assignment 2 introduces Session- and MessageBeans, later ones are dealt with task 2 of the assignment which is separated from task 1.
Assignment 3 extends the GridManagement framework created in Assignment 1+2 and builds a JSF web interface on top of the JavaEE 6 beans. In Part 2 of the Assignment dependency injection is dealt with. The injection is either done via a direct call of IInjectionController.initialize(...) through a middleware framework or via javassist binary injection into the class definition bytes. The 3rd part of the assignment creates a plugin loader framework which uses the dependency injection mechanism created in task 2.

ME - Model Engineering (ME_2011_WS)
==========================================================================================
Lab 1 creates an Eclipse Ecore metamodel called Simple Object-Oriented Modeling Language (SOOML) which is used to describe the functionality of a Mower and ChargingUnit system which utilize a state machine. SOOML defines f.e. Package, Class, Operation, Attributes as well as StateMachine, Transition, Action, Event, ...
Lab 2 consists of 2 parts. The first part develops a Xtext grammer to enable the textual concrete syntax for SOOML which is tested in a generated SOOML editor (at.ac.tuwien.big.me.sw11.SOOMLDsl*). The second part creates OCL queries to ensure the validity of the SOOML model.
Lab 3 introduces a new metamodel called Simple Object-Oriented Programming Language (SOOPL). Atlas Transfromation Language (ATL) ist used to convert SOOML models to SOOPL models.
Lab 4 uses the SOOPL metamodel introduced in the previous Lab to convert the model defined in a .soopl file into Java files with the help of Xtend. Here two different solutions are kept in 2 projects. ME_WS11_Lab4 contains the official solution which has flaws with nested packages, ME_WS11_Lab4_SubPackages does solve this issue but was not required for the Lab (was presented though too)

WFM - Workflow Modeling (WFM_2012_SS)
==========================================================================================
Uses Activiti 5.11 (OpenSource BPMN 2.0 platform) and a couple of mocked web services to simulate an online ordering and paymen process. It uses a JSF 2.0 frontend distributed as web archieve (.war) file to deploy the process definition (.bpmn) and to start and execute the process.