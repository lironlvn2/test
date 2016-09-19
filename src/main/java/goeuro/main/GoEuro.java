package goeuro.main;

public class GoEuro {

	public static void main(String[] args) {
		if (args.length != 1){
			usage();
		} else {
			GoEuroRunner runner = new GoEuroRunner();
			boolean success = runner.getResults(args[0]);
			if (success)
				System.out.println("reasults written to "+GoEuroRunner.OUTPUT_FILE_NAME);
			else
				System.out.println("error. no output");
		}
	}

	private static void usage() {
		System.out.println("This program queries location JSON API in "+GoEuroRunner.GOEURO_URL+ " and transforms the results into csv a file.\n"+ 
				"usage: java -jar GoEuroTest.jar CITY_NAME \n"+
				"output: "+GoEuroRunner.OUTPUT_FILE_NAME);
	}
}
