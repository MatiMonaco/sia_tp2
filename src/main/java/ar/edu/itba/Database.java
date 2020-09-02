package ar.edu.itba;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.ObjectRowProcessor;
import com.univocity.parsers.conversions.Conversions;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Database {
    public static Map<String,Equipment> weapons = new HashMap<>();
    public static Map<String,Equipment> boots = new HashMap<>();
    public static Map<String,Equipment> gloves = new HashMap<>();
    public static Map<String,Equipment> helmets = new HashMap<>();
    public static Map<String,Equipment> armor = new HashMap<>();



    public static Equipment getRandomEquipment(String type){
        Equipment eq;
        String name = getRandomName();
        switch (type){
            case "weapon":

                eq = weapons.get(name);
                eq.type = "weapon";
                break;
            case "helmet":

                eq = helmets.get(name);
                eq.type = "helmet";
                break;
            case "armor":

                eq = armor.get(name);
                eq.type = "armor";
                break;
            case "gloves":

                eq = gloves.get(name);
                eq.type = "gloves";
                break;
            case "boots":

                eq = boots.get(name);
                eq.type = "boots";
                break;

            default:
                throw new IllegalArgumentException();
        }
        return eq;
    }

    public static String getRandomName(){

        return String.valueOf((int)(20 * Math.random()));
    }

    public static void load(){

        TsvParserSettings settings = new TsvParserSettings();
        ObjectRowProcessor bootsRp = new ObjectRowProcessor(){

            @Override
            public void rowProcessed(Object[] row, ParsingContext parsingContext) {
                boots.put((String) row[0], new Equipment("boots",(String) row[0], Double.parseDouble((String) row[1]), Double.parseDouble((String) row[2]), Double.parseDouble((String) row[3]), Double.parseDouble((String) row[4]), Double.parseDouble((String) row[5])));

            }
        };
        ObjectRowProcessor weaponsRp = new ObjectRowProcessor(){

            @Override
            public void rowProcessed(Object[] row, ParsingContext parsingContext) {
                weapons.put((String) row[0], new Equipment("weapon",(String) row[0], Double.parseDouble((String) row[1]), Double.parseDouble((String) row[2]), Double.parseDouble((String) row[3]), Double.parseDouble((String) row[4]), Double.parseDouble((String) row[5])));

            }
        };
        ObjectRowProcessor helmetsRp = new ObjectRowProcessor(){

            @Override
            public void rowProcessed(Object[] row, ParsingContext parsingContext) {
                helmets.put((String) row[0], new Equipment("helmet",(String) row[0], Double.parseDouble((String) row[1]), Double.parseDouble((String) row[2]), Double.parseDouble((String) row[3]), Double.parseDouble((String) row[4]), Double.parseDouble((String) row[5])));

            }
        };
        ObjectRowProcessor glovesRp = new ObjectRowProcessor(){

            @Override
            public void rowProcessed(Object[] row, ParsingContext parsingContext) {
                gloves.put((String) row[0], new Equipment("gloves",(String) row[0], Double.parseDouble((String) row[1]), Double.parseDouble((String) row[2]), Double.parseDouble((String) row[3]), Double.parseDouble((String) row[4]), Double.parseDouble((String) row[5])));

            }
        };
        ObjectRowProcessor armorRp = new ObjectRowProcessor(){

            @Override
            public void rowProcessed(Object[] row, ParsingContext parsingContext) {
                armor.put((String) row[0], new Equipment("armor",(String) row[0], Double.parseDouble((String) row[1]), Double.parseDouble((String) row[2]), Double.parseDouble((String) row[3]), Double.parseDouble((String) row[4]), Double.parseDouble((String) row[5])));

            }
        };

        weaponsRp.convertFields(Conversions.trim(), Conversions.toLowerCase()).set("Description", "Model");
        armorRp.convertFields(Conversions.trim(), Conversions.toLowerCase()).set("Description", "Model");
        helmetsRp.convertFields(Conversions.trim(), Conversions.toLowerCase()).set("Description", "Model");
        glovesRp.convertFields(Conversions.trim(), Conversions.toLowerCase()).set("Description", "Model");
        bootsRp.convertFields(Conversions.trim(), Conversions.toLowerCase()).set("Description", "Model");


        try {
            TsvParser parser;

            System.out.println("Loading weapons...");
                    settings.setRowProcessor(weaponsRp);
                    parser = new TsvParser(settings);
                    parser.parse(new FileReader("./src/main/resources/weapons.tsv"));
            System.out.println("Loading boots...");
                    settings.setRowProcessor(bootsRp);
                    parser = new TsvParser(settings);
                    parser.parse(new FileReader("./src/main/resources/boots.tsv"));
            System.out.println("Loading gloves...");
                    settings.setRowProcessor(glovesRp);
                    parser = new TsvParser(settings);
                    parser.parse(new FileReader("./src/main/resources/helmets.tsv"));
            System.out.println("Loading helmets...");
                    settings.setRowProcessor(helmetsRp);
                    parser = new TsvParser(settings);
                    parser.parse(new FileReader("./src/main/resources/gloves.tsv"));
            System.out.println("Loading armors...");
                    settings.setRowProcessor(armorRp);
                    parser = new TsvParser(settings);
                    parser.parse(new FileReader("./src/main/resources/armor.tsv"));



            System.out.println("Loading complete!");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
