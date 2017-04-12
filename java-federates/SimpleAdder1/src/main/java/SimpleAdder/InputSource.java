// This code has been generated by the C2W code generator.
// Do not edit manually!

package SimpleAdder;

import java.util.Random;

import SimpleAdder.AdderInput;
import SimpleAdder.InputSourceBase;
import c2w.hla.InteractionRoot;
import c2w.hla.ObjectRoot;
import c2w.hla.SubscribedInteractionFilter;

public class InputSource extends InputSourceBase {

	private SubscribedInteractionFilter _subscribedInteractionFilter = new SubscribedInteractionFilter();

	// constructor
	public InputSource(String federation_id, String federate_id)
			throws Exception {
		super(federation_id, federate_id);
	}

	// constructor
	public InputSource(String[] federationInfo) throws Exception {
		super(federationInfo);
	}

	private void execute() throws Exception {
		System.out.println("execute==>");
		double logicalTime = 0;
		InteractionRoot interaction = null;
		ObjectReflector reflector = null;

		AdvanceTimeRequest atr = new AdvanceTimeRequest(logicalTime);
		putAdvanceTimeRequest(atr);

		readyToPopulate();
		Random rand = new Random();
		// NOTE: do initialization that depends on other federates here
		readyToRun();

		startAdvanceTimeThread();

		// executes until the federate terminates
		while (true) {
			// NOTE: change the federate logical step size below
			logicalTime += 1.0;
			System.out.println("time=" + logicalTime);
			atr.requestSyncStart();

			for (int i = 0; i < 2; i++) {
				// Create a new interactionAddClass interaction

				// Populate the message using varName.set_parameterName( value
				// ); (see hla_interaction_setValue)

				// Send the interaction with a timestamp for the given logical
				// time
				AdderInput input = create_AdderInput();
				int randomInteger = rand.nextInt() + 10;
				input.set_value(randomInteger);
				System.out.println("send=" + randomInteger);
				input.sendInteraction(getRTI(), logicalTime);
			}
			// NOTE: send interactions beyond this line (see
			// hla_interaction_send)
			// executes until all interactions from the previous time step are
			// handled
			while ((interaction = getNextInteractionNoWait()) != null) {

			}

			while ((reflector = getNextObjectReflectorNoWait()) != null) {
				// update and retrieve the object instance
				reflector.reflect();
				ObjectRoot object = reflector.getObjectRoot();

			}

			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// DO NOT MODIFY FILE BEYOND THIS LINE
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			AdvanceTimeRequest newATR = new AdvanceTimeRequest(logicalTime);
			putAdvanceTimeRequest(newATR);
			atr.requestSyncEnd();
			atr = newATR;
		}
	}

	private void handleInteractionClass(AdderInput interaction) {
		// user implements this function
	}

	public static void main(String[] args) {
		System.out.println("InputSource main");
		try {
			InputSource instance = new InputSource(args);
			instance.execute();
		} catch (Exception e) {
			System.err.println("Exception caught: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
