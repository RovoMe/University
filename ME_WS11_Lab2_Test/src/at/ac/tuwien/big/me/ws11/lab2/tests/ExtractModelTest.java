package at.ac.tuwien.big.me.ws11.lab2.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.Test;

public class ExtractModelTest {

	private String inputFileName = "my_test.sooml";
	private String outputFileName = "my_test.xmi";

	@Test
	public void extractModel() throws IOException {
		XtextResourceSet xtextResourceSet = new XtextResourceSet();
		Resource xTextResource = xtextResourceSet.getResource(
				URI.createFileURI(new File(inputFileName).getAbsolutePath()),
				true);
		EcoreUtil.resolveAll(xTextResource);

		ResourceSet emfResourceSet = new ResourceSetImpl();
		Resource emfResource = emfResourceSet.createResource(URI
				.createFileURI(new File(outputFileName).getAbsolutePath()));

		emfResource.getContents().addAll(xTextResource.getContents());
		emfResource.save(null);
	}

}
