package team3.Gryffindor.VM;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class JunitRunner {
    public static void main(String[] args) {
        Result customerPanelResult = JUnitCore.runClasses(CustomerPanelTest.class);
        Result machinerySimulatorPanelResult = JUnitCore.runClasses(MachinerySimulatorPanelTest.class);
        Result maintainerPanelResult = JUnitCore.runClasses(MaintainerPanelTest.class);
        Result simulatorControlPanelResult = JUnitCore.runClasses(SimulatorControlPanelTest.class);

        if (customerPanelResult.wasSuccessful()) {
            System.out.println("customer panel tests finished successfully...");
        } else {
            System.out.println("customer panel tests finished unsuccessfully...");
            for (Failure fail : customerPanelResult.getFailures()) {
                System.out.println(fail.toString());
            }
        }

        if (machinerySimulatorPanelResult.wasSuccessful()) {
            System.out.println("machinery simulator panel tests finished successfully...");
        } else {
            System.out.println("machinery simulator panel tests finished unsuccessfully...");
            for (Failure fail : machinerySimulatorPanelResult.getFailures()) {
                System.out.println(fail.toString());
            }
        }

        if (maintainerPanelResult.wasSuccessful()) {
            System.out.println("maintainer panel tests finished successfully...");
        } else {
            System.out.println("maintainer panel tests finished unsuccessfully...");
            for (Failure fail : maintainerPanelResult.getFailures()) {
                System.out.println(fail.toString());
            }
        }

        if (simulatorControlPanelResult.wasSuccessful()) {
            System.out.println("simulator control panel tests finished successfully...");
        } else {
            System.out.println("simulator control panel tests finished unsuccessfully...");
            for (Failure fail : simulatorControlPanelResult.getFailures()) {
                System.out.println(fail.toString());
            }
        }
        System.exit(0);
    }
}
