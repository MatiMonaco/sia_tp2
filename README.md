# sia_tp2
Compilación:

Al ser programado en Java, es necesario que descarguen como mínimo la SDK 8.
Al ser un proyecto Maven, para compilar y crear el .jar se debe usar el comando 'mvn clean package', el cual crea el .jar dentro de la carpeta sia_tp2/target.
En el caso de tener los archivos de configuracion config.json ya en esa carpeta, el comando anterior los borrará y deben crearse de nuevo.

Ejecución:

Dentro de la carpeta sia_tp2/target se encuentra el ejecutable sia_tp2-1.0-SNAPSHOT.jar, las dependencias, y config.json. 

Dentro de config.json se ingresan ingresan los parametros de ejecución:
  - initialPopulation: tamaño de población inicial,
  - newGenerationSize: tamaño de la nueva generación,
  - selectionSize: tamaño de la selección de padres,
  - characterType": clase de los personajes ["warrior","archer","infiltrator","defender"],

   - crossing: método de cruza ["onePointCrossing","twoPointCrossing","annularCrossing","uniformCrossing"],
   - mutation": método de mutación ["genMutation","multiGenMutation","multiGenUniform","complete"],
   - mutationChance: probabilidad de un genoma de mutar,

  - pa: procentaje de la población sobre la cual se aplica el método de selección A, y
        en consecuencia se aplica al (1-pa)% de la población el método de selección B,
  - selectionA: método de selección A de padres ["boltzmann","deterministicTournament","probabilisticTournament","ranking",""elite","universal"],
  - selectionB: método de selección B de padres ["boltzmann","deterministicTournament","probabilisticTournament","ranking",""elite","universal"],
    - boltzmann_initialTemp: temperatura inicial en la que inicia el método de Boltzmann,
    - boltzmann_finalTemp: temperatura inicial en la que finaliza el método de Boltzmann,
    - dT_m: cantidad de ciclos que se realizan en cada selección de ganadores del torneo determinístico,
    - pT_tournamentThreshold: límite utilizado en el torneo probabilístico,
    
  - pb: procentaje de la población sobre la cual se aplica el método de reemplazo A, y
        en consecuencia se aplica al (1-pb)% de la población el método de reemplazo B,
  - replacementA: método de reemplazo A de padres ["boltzmann","deterministicTournament","probabilisticTournament","ranking",""elite","universal"],
  - replacementB: método de reemplazo B de padres ["boltzmann","deterministicTournament","probabilisticTournament","ranking",""elite","universal"],

  - cut: método de corte ["acceptableSolution","content","generationQuantity","structure","time"],
    - aS_expected: valor esperado del fitness en el método de corte "acceptableSolution",
    - aS_delta: error contemplado en la comparación de fitness en el método de corte "acceptableSolution",
    - content_generationLimit: límite de generaciones en el método de corte "content",
    - content_delta: error contemplado en la comparación de fitness en el método de corte "content",
    - gQ_generationLimit: límite de generaciones en el método de corte "generationLimit",
    - str_populationPercentage: porcentaje de la población sobre la cual se busca que no cambie su estructura,
    - str_generationLimit: límite de generaciones en el método de corte "structure",
    - time_limit: tiempo límite en segundos en el método de corte "time",
    
   - implementation: tipo de implementación ["fillAll", "fillParent"], 
   - error: error a tener en cuenta en la igualdad de números de doble presición, 




Ejemplo de configuración de ejecución:

{
  "initialPopulation": "1000",
  "newGenerationSize": "50",
  "selectionSize": "10",

  "characterType": "infiltrator",

  "crossing": "uniformCrossing",

  "mutation": "multiGenMutation",
  "mutationChance": "0.9",

  "pa": "0.5",
  "selectionA": "elite",
  "selectionB": "ranking",

  "pb": "0.5",
  "replacementA": "roulette",
  "replacementB": "elite",

  "cut": "content",

  "implementation": "fillAll",
  "error": "0.0001",
  
  "content_generationLimit": "50",
  "content_delta": "0.05"
}
