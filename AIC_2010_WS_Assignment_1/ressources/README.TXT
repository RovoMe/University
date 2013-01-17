addressing.xsd represents a schema which defines how service endpoints are being addressed. 
To generate java-Files we can work with, we have to first transform the xsd-file into a wsdl-file.

apache cxf provides the xsd2wsdl-script therefore. Options in our case:

xsd2wsdl -t <target-name-space> -n <wsdl-name> -d <output-directory> -o <output-file> -h -v -verbose -quiet <xsdurl> 

Options: 
   -t <target-name-space>   Specify the target namespace.
   -n <wsdl-name>           The name of generated wsdl.
   -d <output-directory>    The directory in which the generated wsdl is placed
   -o <output-file>         The wsdl output file name.
   -h                       Display detailed information for options.
   -v                       Display the version of the tool.
   -verbose                 Verbose mode
   -quiet                   Quiet mode
   <xsdurl>                 XSD URL

xsd2wsdl -t ##default -n addressing.wsdl -d . -o addressing.wsdl addressing.xsd

afterwards this generated wsdl-file can be transformed into java-files with 

wsdl2java -fe <front-end-name>* -db <data-binding-name>* -wv <wsdl-version>* -p <[wsdl-namespace =]package-name>* -sn <service-name> -b <binding-file-name>* -catalog <catalog-file-name> -d <output-directory> -compile -classdir <compile-classes-directory> -impl -server -client -all -autoNameResolution -allowElementReferences<=true> -defaultValues<=class-name-for-DefaultValueProvider> -ant -nexclude <schema-namespace [= java-package-name]>* -exsh <(true, false)> -dns <Default value is true> -dex <(true, false)> -validate -keep -wsdlLocation <wsdlLocation> -xjc<xjc-arguments> -noAddressBinding -h -v -verbose -quiet -wsdlList <wsdlurl> 

Options: 
   -fe <front-end-name>*
                     Specifies the front end. (defaults to JAXWS)

   -db <data-binding-name>*
                     Specifies the data binding. (defaults to JAXB)

   -wv <wsdl-version>*
                     Specifies the WSDL version. (default is WSDL1.1)

   -p <[wsdl-namespace =]package-name>*
                     Specifies the java package name to use for the generated
                     code. Optionally specify a WSDL namespace to Java package
                     name mapping.

   -sn <service-name>
                     Specify he WSDL service name to use for the generated code.
                     Also, optionally specify the WSDL namespace.

   -b <binding-file-name>*
                     Specify an external jaxws or jaxb binding files. Use one -b
                     flag for each binding file.

   -catalog <catalog-file-name>
                     Specify catalog file to map the imported wsdl/schema.

   -d <output-directory>
                     Specify the directory into which the code is placed.

   -compile          Specifies that the generated code is compiled by the tool.

   -classdir <compile-classes-directory>
                     Specifies the directory into which compiled class files are
                     placed.

   -impl             Specifies that a dummy service implementation is generated.

   -server           Specifies that server code is generated.

   -client           Specifies that client code is generated.

   -all              Specifies that interfaces, types , service, server , dummy
                     impl, client and ant script are generated.

   -autoNameResolution
                     Specifies that the tool will attempt to resolve class
                     naming conflicts without requiring the use of binding
                     customizations.

   -allowElementReferences<=true>
                     allowElementReferences

   -defaultValues<=class-name-for-DefaultValueProvider>
                     Specifies that default values are generated for the dummy
                     implementation and client. You can specify the name of the
                     class to provide the default values. The default is
                     RandomValueProvider.

   -ant              Specifies that an ant build script is generated for the
                     project.

   -nexclude <schema-namespace [= java-package-name]>*
                     Specifies a WSDL namespace to exclude when generating code.
                     This option can be specified multiple times. Optionally
                     specify the Java package name to use for the WSDL
                     namespace.

   -exsh <(true, false)>
                     Enables the processing of extended SOAP header message
                     binding.

   -dns <Default value is true>
                     Enables loading the default namespace package name mapping.
                     The default is true.

   -dex <(true, false)>
                     Enable loading the default excludes namespace mapping. The
                     default is true.

   -validate         Specifies that the WSDL is validated before generating the
                     code. Using this option is highly recommended.

   -keep             Specifies that existing code will not be over written.
                     NOTE: You will have to solve any resulting compilation
                     problems by yourself

   -wsdlLocation <wsdlLocation>
                     Specifies the value of the @WebServiceClient annotation's
                     wsdlLocation property.

   -xjc<xjc-arguments>
                     Specifies a comma separated list of arguments that are
                     passed directly to XJC when the JAXB data binding is used.
                     This option causes XJC to load additional plugins that
                     augment code generation. For example to load the
                     toString(ts) plugin that will add a toString() method to
                     all generated types the following <xjc arguments> would be
                     used: -xjc-Xts A list of available XJC plugins can be
                     obtained by using -xjc-X.

   -noAddressBinding Specifies that the generator should not use the address
                     jaxb binding file to map wsa:EndpointReferenceType or
                     wsa:EndpointReference to
                     javax.xml.ws.wsaddressing.W3CEndpointReference.

   -h                Display detailed information for options.

   -v                Display the version of the tool.

   -verbose          Specifies that the generator runs in verbose mode.

   -quiet            Specifies that the generator runs in quiet mode.

   -wsdlList         Indicates the wsdlurl is a plain text list of wsdlurls that
                     are new line delimited. As an example the wsdlurl might
                     point to
                     http://127.0.0.1:8080/context_path/ws?formatted=false&wsdlL
                     ist=true on a cxf server.

   <wsdlurl>         wsdl-url


wsdl2java addressing.wsdl

xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

The JAXB XJC schema binding compiler transforms, or binds, a source XML schema to a set of 
JAXB content classes in the Java programming language

Usage: xjc [-options ...] <schema file/URL/dir/jar> ... [-b <bindinfo>] ...
If dir is specified, all schema files in it will be compiled.
If jar is specified, /META-INF/sun-jaxb.episode binding file will be compiled.
Options:
  -nv                :  do not perform strict validation of the input schema(s)
  -extension         :  allow vendor extensions - do not strictly follow the
                        Compatibility Rules and App E.2 from the JAXB Spec
  -b <file/dir>      :  specify external bindings files (each <file> must have its own -b)
                        If a directory is given, **/*.xjb is searched
  -d <dir>           :  generated files will go into this directory
  -p <pkg>           :  specifies the target package
  -httpproxy <proxy> :  set HTTP/HTTPS proxy. Format is [user[:password]@]proxyHost:proxyPort
  -httpproxyfile <f> :  Works like -httpproxy but takes the argument in a file to protect password 
  -classpath <arg>   :  specify where to find user class files
  -catalog <file>    :  specify catalog files to resolve external entity references
                        support TR9401, XCatalog, and OASIS XML Catalog format.
  -readOnly          :  generated files will be in read-only mode
  -npa               :  suppress generation of package level annotations (**/package-info.java)
  -no-header         :  suppress generation of a file header with timestamp
  -target 2.0        :  behave like XJC 2.0 and generate code that doesnt use any 2.1 features.
  -xmlschema         :  treat input as W3C XML Schema (default)
  -relaxng           :  treat input as RELAX NG (experimental,unsupported)
  -relaxng-compact   :  treat input as RELAX NG compact syntax (experimental,unsupported)
  -dtd               :  treat input as XML DTD (experimental,unsupported)
  -wsdl              :  treat input as WSDL and compile schemas inside it (experimental,unsupported)
  -verbose           :  be extra verbose
  -quiet             :  suppress compiler output
  -help              :  display this help message
  -version           :  display version information


Extensions:
  -Xlocator          :  enable source location support for generated code
  -Xsync-methods     :  generate accessor methods with the 'synchronized' keyword
  -mark-generated    :  mark the generated code as @javax.annotation.Generated
  -episode <FILE>    :  generate the episode file for separate compilation

xjc -d ../src -p org.xmlsoap.schemas.addressing addressing.xsd 

xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

java2ws -databinding <jaxb or aegis> -frontend <jaxws or simple> -wsdl -wrapperbean -client -server -ant -o <output-file> -d <resource-directory> -s <source-directory> -classdir <compile-classes-directory> -cp <class-path> -soap12 -t <target-namespace> -beans <pathname of the bean definition file.>* -servicename <service-name> -portname <port-name> -address <address> -createxsdimports -h -v -verbose -quiet <classname> 

Options: 

   -databinding <jaxb or aegis>
                     Specify the data binding (aegis or jaxb). Default is jaxb
                     for jaxws frontend, and aegis for simple frontend.

   -frontend <jaxws or simple>
                     Specify the frontend to use. jaxws and the simple frontend
                     are supported.

   -wsdl             Specify to generate the WSDL file

   -wrapperbean      Specify to generate the wrapper and fault bean

   -client           Specify to generate client side code

   -server           Specify to generate server side code

   -ant              Specify to generate an Ant build.xml file

   -o <output-file>  Specify the output wsdl file name

   -d <resource-directory>
                     The directory in which the output wsdl and other resource
                     files are placed

   -s <source-directory>
                     The directory in which the generated java source files are
                     placed

   -classdir <compile-classes-directory>
                     The directory in which the generated wrapper sources are
                     compiled into. If not specified, the files are not
                     compiled.

   -cp <class-path>  Specify the SEI and types class search path of directories
                     and zip/jar files

   -soap12           Specify to generate SOAP 1.2 binding

   -t <target-namespace>
                     Specify the target namespace to use in the wsdl

   -beans <pathname of the bean definition file.>*
                     Specify the pathname of a file defining additional Spring
                     beans to customize databinding configuration.

   -servicename <service-name>
                     Specify the service name to use in the generated wsdl

   -portname <port-name>
                     Specify the port name to use in the generated wsdl

   -address <address>
                     Specify the port address

   -createxsdimports Output schemas to separate files and use imports to load
                     them instead of inlining them into the wsdl.

   -h                Display detailed information for options.

   -v                Display the version of the tool.

   -verbose          Verbose mode

   -quiet            Quiet mode

   <classname>       SEI class name

   
java2ws -wsdl -d . -cp ../bin/ webService.impl.Supplier1
java2ws -wsdl -d . -cp ../bin/ webService.impl.Supplier2