package ar.edu.itba;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.ObjectRowProcessor;
import com.univocity.parsers.conversions.Conversions;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Character warrior = new Character(Character.CharacterClass.WARRIOR);

        System.out.println(warrior.type);
        System.out.println(warrior.type.getBaseAttackM());
        System.out.println(warrior.type.getBaseDefenseM());

        TsvParserSettings settings = new TsvParserSettings();

        // all rows parsed from your input will be sent to this processor
        ObjectRowProcessor rowProcessor = new ObjectRowProcessor() {
            @Override
            public void rowProcessed(Object[] row, ParsingContext context) {
                //here is the row. Let's just print it.
                System.out.println(Arrays.toString(row));
            }
        };
// the ObjectRowProcessor supports conversions from String to whatever you need:
// converts values in columns 2 and 5 to BigDecimal
        rowProcessor.convertIndexes(Conversions.toBigDecimal()).set(2, 5);

// converts the values in columns "Description" and "Model". Applies trim and to lowercase to the values in these columns.
        rowProcessor.convertFields(Conversions.trim(), Conversions.toLowerCase()).set("Description", "Model");

//configures to use the RowProcessor
        settings.setRowProcessor(rowProcessor);

        TsvParser parser = new TsvParser(settings);
//parses everything. All rows will be pumped into your RowProcessor.
        try {
            parser.parse(new FileReader("C:\\Users\\Ignacio Vazquez\\IdeaProjects\\sia_tp2\\src\\main\\Resources\\armas.tsv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
