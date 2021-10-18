package availty.csvsorter;

import availty.Users;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CSVSorter {

    public static void main(String[] args) throws IOException {
        List<Users> users = new ArrayList<>();
        String[] values = null;
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("i1.csv"));
            while ((line = reader.readLine()) != null)
            {
                values = line.split(",");
                Users user = createUser(values);
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String ,List<Users>> groupByInsuranceGroups = users.stream().collect(Collectors.groupingBy(Users::getInsuranceCompany));

        System.out.println(groupByInsuranceGroups);

        for (List<Users> usersList: groupByInsuranceGroups.values()) {
            usersList.sort(Comparator.comparing(Users::getLastName));
            String insuranceCompany = usersList.get(0).getInsuranceCompany();
            createCSV(usersList, insuranceCompany);
        }


    }

    private static Users createUser(String[] metadata) {
        String userId=metadata[0];
        String firstName=metadata[1];
        String lastName=metadata[2];
        int version=Integer.parseInt(metadata[3]);
        String insuranceCompany=metadata[4];

        return new Users(userId, firstName, lastName, version, insuranceCompany);
    }


    public static void createCSV(List<Users> list, String ic) throws IOException {
        FileWriter fileWriter = null;

        fileWriter = new FileWriter(ic+".csv");
        Iterator it = list.iterator();
        while(it.hasNext())
        {
            Users users = (Users) it.next();
            fileWriter.append(users.getUserId());
            fileWriter.append(",");
            fileWriter.append(users.getFirstName());
            fileWriter.append(",");
            fileWriter.append(users.getLastName());
            fileWriter.append(",");
            fileWriter.append(String.valueOf(users.getVersion()));
            fileWriter.append(",");
            fileWriter.append(users.getInsuranceCompany());
        }

        fileWriter.close();

    }
}
