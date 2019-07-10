package RiskFramework;

public class expectedDamage extends reputationIntegrated {

    final String expected_damage_file = "e:\\r2q\\expectedDamage.txt";
    double expectedDamage;
    double confidentiality, integrity, availability;
    int sensitive = 1;

    public void calculateExpectedDamage() {

        //calculate if ssensitive or non-sensitive..
        if ((sensitivityBuffer.toString().equals("Unclassified"))) {
            sensitive = 0;
        } else {
            sensitive = 1;
        }

        if (accessModeBuffer.toString().equals("View") && sensitive == 1) //view,sensitive
        {
            confidentiality = 1;
            integrity = 0;
            availability = 0;
        } else if (accessModeBuffer.toString().equals("Edit") && sensitive == 1) //edit,sensitive
        {
            confidentiality = 0;
            integrity = 1;
            availability = 1;
        } else if (accessModeBuffer.toString().equals("Edit") && sensitive == 0) //edit ,non-sensitive
        {
            confidentiality = 0;
            integrity = 1;
            availability = 1;
        } else if (accessModeBuffer.toString().equals("Execute") && sensitive == 1) //execute,sensitive..
        {
            confidentiality = 0;
            integrity = 1;
            availability = 1;
        } else if (accessModeBuffer.toString().equals("Execute") && sensitive == 0) //execute,non-sensitive
        {
            confidentiality = 0;
            integrity = 1;
            availability = 1;
        } else {
            confidentiality = 0;
            integrity = 0;
            availability = 0;
        }

        if (accessModeBuffer.toString().equals("View")) {
            expectedDamage = (confidentiality + integrity + availability) * view_probability;
        } else if (accessModeBuffer.toString().equals("Edit")) {
            expectedDamage = (confidentiality + integrity + availability) * edit_probability;
        } else if (accessModeBuffer.toString().equals("Execute")) {
            expectedDamage = (confidentiality + integrity + availability) * execute_probability;
        }

    }

    public void assignDamage() {
        //write to file and clearing buffers..
        writerTextBuffer.append(sb).append("\t").append(expectedDamage).append("\n");
        appendToFile(expected_damage_file, writerTextBuffer.toString());

        //This shall occur after manipulation of extracted data..
        clearBuffers();
    }
}
//next Simulation..
