
package at.ac.tuwien.big.me.sw11;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class SOOMLDslStandaloneSetup extends SOOMLDslStandaloneSetupGenerated{

	public static void doSetup() {
		new SOOMLDslStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

