package ar.edu.itba;

import ar.edu.itba.classes.Character;
import ar.edu.itba.classes.CharacterType;
import ar.edu.itba.convergences.*;
import ar.edu.itba.selections.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.function.BiFunction;


public class Main {



    public static void main(String[] args) throws InterruptedException {

        JSONParser jsonParser = new JSONParser();
        String path = "./config.json";
        Database.load();

        int initialPopulation, selectionSize, newGenerationSize;
        double mutationChance;
        double pa,pb;
        double error;
        BiFunction<Character,Character,List<Character>> crossingMethod;
        BiFunction<Character,Double,Void> mutationMethod;
        Selection selectionMethodA;
        Selection selectionMethodB;
        Selection replacementMethodA;
        Selection replacementMethodB;
        Convergence cutMethod;
        CharacterType characterType ;
        GeneticAlgorithm ga;

        try (Reader reader = new FileReader(path)) {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            System.out.println(jsonObject);

            String err = (String) jsonObject.get("error");
            if (err == null)
                throw new IllegalArgumentException("Se debe especificar el error contemplado");
            error = Double.parseDouble(err);

            String paS = (String) jsonObject.get("pa");
            if (paS == null)
                throw new IllegalArgumentException("Se debe especificar el porcentaje sobre el cual usar el metodo de seleccion A");
            pa = Double.parseDouble(paS);

            String pbS = (String) jsonObject.get("pb");
            if (pbS == null)
                throw new IllegalArgumentException("Se debe especificar el porcentaje sobre el cual usar el metodo de reemplazo A");
            pb = Double.parseDouble(paS);


            String mutChance = (String) jsonObject.get("mutationChance");
            if (mutChance == null)
                throw new IllegalArgumentException("Se debe especificar la probabilidad de mutacion");
            mutationChance = Double.parseDouble(mutChance);

            String initPop = (String) jsonObject.get("initialPopulation");
            if (initPop == null)
                throw new IllegalArgumentException("Se debe especificar la poblacion inicial");
            initialPopulation = Integer.parseInt(initPop);

            String newGen = (String) jsonObject.get("newGenerationSize");
            if (newGen == null)
                throw new IllegalArgumentException("Se debe especificar el tamaño de la nueva generacion");
            newGenerationSize = Integer.parseInt(newGen);

            String selectSize = (String) jsonObject.get("selectionSize");
            if (selectSize == null)
                throw new IllegalArgumentException("Se debe especificar el tamaño de la seleccion");
            selectionSize = Integer.parseInt(selectSize);

            String type = (String) jsonObject.get("characterType");
            if (type == null) {
                throw new IllegalArgumentException("Se debe especificar el tipo de personaje");
            }else{
                switch (type){
                    case "warrior":
                        characterType = CharacterType.WARRIOR;
                        break;
                    case "archer":
                        characterType = CharacterType.ARCHER;
                        break;
                    case "defender":
                        characterType = CharacterType.DEFENDER;
                        break;
                    case "infiltrator":
                        characterType = CharacterType.INFILTRATOR;
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo de personaje invalido");
                }
            }

            String crossing = (String) jsonObject.get("crossing");
            if(crossing == null){
                throw new IllegalArgumentException("Se debe especificar el cruce a utilizar");
            }else{
                switch (crossing){
                    case "onePointCrossing":
                        crossingMethod = Crossing::onePointCrossing;
                        break;
                    case "twoPointCrossing":
                        crossingMethod = Crossing::twoPointCrossing;
                        break;
                    case "annularCrossing":
                        crossingMethod = Crossing::annularCrossing;
                        break;
                    case "uniformCrossing":
                        crossingMethod = Crossing::uniformCrossing;
                        break;
                    default:
                        throw new IllegalArgumentException("Metodo de cruza invalido");
                }
            }

            String mutation = (String) jsonObject.get("mutation");
            if(mutation == null){
                throw new IllegalArgumentException("Se debe especificar el mutación a utilizar");
            }else{
                switch (mutation){
                    case "genMutation":
                        mutationMethod = Mutation::genMutation;
                        break;
                    case "multiGenMutation":
                        mutationMethod = Mutation::multiGenMutation;
                        break;
                    case "multiGenUniform":
                        mutationMethod = Mutation::multiGenUniform;
                        break;
                    case "complete":
                        mutationMethod = Mutation::complete;
                        break;
                    default:
                        throw new IllegalArgumentException("Metodo de mutación invalido");
                }
            }

            String selectionA = (String) jsonObject.get("selectionA");
            if(selectionA == null){
                throw new IllegalArgumentException("Se debe especificar el metodo de seleccion de padres");
            }else{
                selectionMethodA = getSelectionInstance(selectionA, jsonObject);
            }

            String selectionB = (String) jsonObject.get("selectionB");
            if(selectionB == null){
                throw new IllegalArgumentException("Se debe especificar el metodo de seleccion de padres");
            }else{
                selectionMethodB = getSelectionInstance(selectionB, jsonObject);
            }

            String replacementA = (String) jsonObject.get("replacementA");
            if(replacementA == null){
                throw new IllegalArgumentException("Se debe especificar el metodo de remplazo de padres");
            }else{
                replacementMethodA = getSelectionInstance(replacementA, jsonObject);
            }

            String replacementB = (String) jsonObject.get("replacementB");
            if(replacementB == null){
                throw new IllegalArgumentException("Se debe especificar el metodo de remplazo de padres");
            }else{
                replacementMethodB = getSelectionInstance(replacementB, jsonObject);
            }

            String cut = (String) jsonObject.get("cut");
            if(cut == null){
                throw new IllegalArgumentException("Se debe especificar el criterio de corte a utilizar");
            }else{
                String delta;
                String generationLimit;
                switch (cut){
                    case "acceptableSolution":
                        String expected = (String) jsonObject.get("aS_expected");
                        delta = (String) jsonObject.get("aS_delta");
                        if (expected == null || delta == null)
                            throw new IllegalArgumentException("Se debe especificar la solucion esperada y el delta");
                        cutMethod = new AcceptableSolutionConvergence(Double.parseDouble(expected), Double.parseDouble(delta));
                        break;
                    case "content":
                        generationLimit = (String) jsonObject.get("content_generationLimit");
                        delta = (String) jsonObject.get("content_delta");
                        if (generationLimit == null || delta == null)
                            throw new IllegalArgumentException("Se debe especificar la maxima generacion y el delta");
                        cutMethod = new ContentConvergence(Integer.parseInt(generationLimit), Double.parseDouble(delta));
                        break;
                    case "generationQuantity":
                        generationLimit = (String) jsonObject.get("gQ_generationLimit");
                        if (generationLimit == null)
                            throw new IllegalArgumentException("Se debe especificar la maxima generacion");
                        cutMethod = new GenerationQuantityConvergence(Integer.parseInt(generationLimit));
                        break;
                    case "structure":
                        String populationPercentage = (String) jsonObject.get("str_populationPercentage");
                        generationLimit = (String) jsonObject.get("str_generationLimit");

                        if (generationLimit == null || populationPercentage == null)
                            throw new IllegalArgumentException("Se debe especificar el porcentaje de poblacion, la maxima generacion y el error");
                        cutMethod = new StructureConvergence(Double.parseDouble(populationPercentage), Integer.parseInt(generationLimit), error);
                        break;
                    case "time":
                        String limit = (String) jsonObject.get("time_limit");
                        if (limit == null)
                            throw new IllegalArgumentException("Se debe especificar el tiempo limite en segundos");
                        cutMethod = new TimeConvergence(Long.parseLong(limit),System.currentTimeMillis());
                        break;
                    default:
                        throw new IllegalArgumentException("Metodo de corte invalido");
                }
            }

            String implementation = (String) jsonObject.get("implementation");
            if(implementation == null){
                throw new IllegalArgumentException("Se debe especificar la implementación a utilizar");
            }else{
                switch (implementation){
                    case "fillAll":
                        ga = new FillAll(initialPopulation, selectionSize, selectionMethodA, selectionMethodB, replacementMethodA, replacementMethodB,
                                newGenerationSize, mutationChance, pa, pb, cutMethod,
                                crossingMethod, mutationMethod, characterType, error);
                        ga.start();
                        break;
                    case "fillParent":
                        ga = new FillParent(initialPopulation, selectionSize, selectionMethodA, selectionMethodB, replacementMethodA, replacementMethodB,
                                newGenerationSize, mutationChance, pa, pb, cutMethod,
                                crossingMethod, mutationMethod, characterType, error);
                        ga.start();
                        break;
                    default:
                        throw new IllegalArgumentException("Metodo de implementacion invalido");
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

//        GeneticAlgorithm fillAll = new FillParent(20,10,new Roulette(),new Universal(),new ProbabilisticTournament(0.9),null,
//                30,0.1,0.5,1, new StructureConvergence(0.9,10,0.01),
//                Crossing::uniformCrossing,Mutation::multiGenMutation,CharacterType.WARRIOR,0.01);
//
//        fillAll.start();
    }

    private static Selection getSelectionInstance(String selection, JSONObject jsonObject){
        switch (selection){
            case "boltzmann":
                String initialTemp = (String) jsonObject.get("botlzmann_initialTemp");
                if(initialTemp == null)
                    throw new IllegalArgumentException("Se debe especificar una temperatura inicial para el metodo de Boltzmann");

                String finalTemp = (String) jsonObject.get("botlzmann_finalTemp");
                if(finalTemp == null)
                    throw new IllegalArgumentException("Se debe especificar una temperatura final para el metodo de Boltzmann");
                return  new Boltzmann(Double.parseDouble(initialTemp), Double.parseDouble(finalTemp));
            case "deterministicTournament":
                String m = (String) jsonObject.get("dT_m");
                if (m == null)
                    throw new IllegalArgumentException("Se debe especificar una cantidad para generar los duelos del torneo");
                return new DeterministicTournament(Integer.parseInt(m));
            case "elite":
                return new Elite();
            case "probabilisticTournament":
                String tournamentThreshold = (String) jsonObject.get("pT_tournamentThreshold");
                if (tournamentThreshold == null)
                    throw new IllegalArgumentException("Se debe especificar un threshold para el torneo probabilistico y este debe ser un valor entre 0.5 y 1");
                return new ProbabilisticTournament(Double.parseDouble(tournamentThreshold));
            case "ranking":
                return new Ranking();
            case "roulette":
                return new Roulette();
            case "universal":
                return new Universal();
            default:
                throw new IllegalArgumentException("Metodo de seleccion invalido");
        }
    }
}
