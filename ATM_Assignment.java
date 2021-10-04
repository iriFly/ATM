import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/*Use-case

                                  ***Bank ATM***

Beschrijving van use-case: Hiermee kan elke bankklant contant geld van zijn bankrekening krijgen.
Deze use-case begint wanneer een ATM-klant een type rekening kiest waarvan het geld moet
worden opgenomen (bijvoorbeeld controleren) uit een lijst met mogelijke rekeningen en om een
dollarbedrag te kiezen uit een lijst met mogelijke bedragen. Het systeem stuurt de transactie ter
verificatie naar het financiële systeem. Als het financiële systeem de transactie goedkeurt, geeft
de machine de juiste hoeveelheid contant geld af en geeft een ontvangstbewijs af. De
verstrekking van contant geld wordt ook geregistreerd in het logboek van de geldautomaat.
We loggen elke transactie in het logboek van ATM met behulp van een String-array.

Naam use-case:
Geld opnemen

Acteurs:
Elke bankklant (heeft een bestaande rekening)
Banksysteem (de geldautomaat, ATM, van elke bank en haar infrastructuur)

Triggers:
De gebruiker wil geld opnemen van een van zijn/haar account.

Voorwaarden:
De geldautomaat is operationeel.
De bankklant heeft een kaart om in de geldautomaat te plaatsen.

Postvoorwaarden:
De bankklant heeft zijn contant geld (en eventueel een ontvangstbewijs) ontvangen.
De bank heeft de bankrekening van de klant gedebiteerd en de details van de transactie
geregistreerd.

03.01: Normale stroom (scenario): (10 punten)
De klant voert zijn kaart in bij de geldautomaat.
De geldautomaat controleert of de kaart een geldige bankkaart is.
De geldautomaat vraagt om een pincode.
De klant voert zijn pincode in.
De geldautomaat valideert de bankkaart aan de hand van de pincode.
De geldautomaat biedt serviceopties, waaronder "Opnemen".
De klant kiest voor "Opnemen".
De geldautomaat controleert voldoende geld op de bankrekening van de klant.
De geldautomaat biedt opties voor bedragen.
De klant selecteert een bedrag of voert een bedrag in.
De geldautomaat controleert of de klant onder de opnamelimieten zit.
De geldautomaat controleert of hij genoeg geld in zijn hopper heeft.
De geldautomaat debiteert de bankrekening van de klant.
De geldautomaat retourneert de bankkaart van de klant.
De klant neemt zijn bankkaart.
De geldautomaat geeft het geld van de klant uit.
De klant neemt het geld.
Use case eindigt.


03.02: De gebruiker heeft een ongeldige kaart. Dit kan worden veroorzaakt door de staat van
de kaart, d.w.z. de kaart is gebroken, gebogen of de magnetische streep / computerchip is
beschadigd of de gecodeerde gegevens worden gewist, geblokkeerd of niet geautoriseerd
account, geactiveerde kaart en / of verkeerd geplaatste kaart, d.w.z. kaart is ondersteboven.
De ATM geeft een foutbericht weer.
De geldautomaat zal de kaart uitwerpen.
De gebruiker neemt de kaart.
De use case keert terug naar de eerste stap en gaat verder.
03.03: De gebruiker heeft een ongeldige pincode ingevoerd.


De geldautomaat geeft aan dat de verkeerde pincode is ingevoerd.
Het systeem registreert en controleert het aantal pogingen.
De geldautomaat zal de klant vragen om zijn pincode opnieuw in te voeren.
De use case keert terug naar de pincode-invoer stap en gaat verder.


03.04: De gebruiker heeft het aantal pincodepogingen overschreden.
De geldautomaat behoudt de kaart van de gebruiker.
De geldautomaat legt een videobeeld van 10 seconden van de klant vast.
De ATM maakt een gebeurtenislogboekvermelding om vast te leggen dat de klant in drie
pogingen niet het juiste pincode heeft ingevoerd.
De geldautomaat stuurt de gebeurtenislogboekvermelding naar het banksysteem.
De use case eindigt.


03.05: De klant heeft onvoldoende geld op de bankrekening staan.
De geldautomaat zal de klant informeren dat de bank de opname heeft geweigerd.
De geldautomaat zal de gebruiker adviseren om contact op te nemen met de bank voor
meer informatie.
Het systeem registreert een transactielogboekpost voor de transactie, inclusief de reden die
is opgegeven voor de afwijzing van de transactie.
De use case eindigt.


03.06: De gebruiker probeert contant geld op te nemen boven de dagelijkse
opnamelimieten.
De geldautomaat geeft een foutbericht weer waarin de dagelijkse opnamelimiet wordt
uitgelegd.
De geldautomaat vraagt de klant om een kleiner bedrag in te voeren.
De use case keert terug naar stap de menu en gaat verder.


03.07: De ATM hopper heeft niet genoeg contant geld.
De ATM geeft een foutbericht weer.
De geldautomaat geeft de maximale beschikbare opnamelimiet voor contant geld weer.
Het systeem maakt een gebeurtenislogboekvermelding om vast te leggen dat de
geldautomaat geen contant geld meer heeft.
Het systeem stuurt de gebeurtenislogboekvermelding naar de bank.
De gebruiker voert een kleiner bedrag in.
De use case keert terug naar de menu en gaat verder.


03.08: De gebruiker selecteert om de sessie af te sluiten.
De gebruiker annuleert het proces.
De geldautomaat zal de kaart uitwerpen.
De gebruiker neemt de kaart.
De use case eindigt.
*/

public class Opdracht3 {

    public static void main(String[] args) {

        //TIME_VARIABLES
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        //PIN_CODE VARIABLE
        int pinCodeChecker = 1234;

        //CARD_NUMBER_LENGTH_CHECKER VARIABLE
        long cardNumberChecker = 1234567812345678L;

        //EXPIRATION_DATE CHECKER
        int expirationDateChecker = 1111;

        //BALANCE VARIABLE
        int balance = 5000;

        //STARTING_VARIABLE  -> ACCOUNT NUMBER && EXPIRATION DATE
        long accountNumber = 0;
        int expirationDate = 0;

        //DAILY LIMIT VARIABLE
        int dailyLimit = 2000;

        //LOGGER ARRAY
        String logBook[] = new String[1000];

        //STARTING_VARIABLE COUNTER -> LOGGER ARRAY
        int counter = 0;

        //STARTING_VARIABLE DAY_LIMIT_CHECKER
        int dayLimitChecker = 0;

        //RANDOM ATM HOPPER LIMITS
        int minHopper = 10;
        int maxHopper = 20000;

        //RANDOM DAMAGED_CARD_RANDOMIZER LIMITS
        int DCR_MIN = 1;
        int DCR_MAX = 5;
        //Generate random int value from 10 to Hopper limit of 20.000
        int atmHopperRandom = (int) (Math.random() * (maxHopper - minHopper + 1) + minHopper);

        //Generate random int value from 1 to 5
        int damagedCardRandomizer = (int) (Math.random() * (DCR_MAX - DCR_MIN + 1) + DCR_MIN);

        //SCANNER
        Scanner scanner = new Scanner(System.in);


        //WELCOME_MESSAGE
        String welcomeMessage = "" +
                ".######..##..##..######..##..##..#####....####...........#####....####...##..##..##..##.\n" +
                ".##......##..##....##....##..##..##..##..##..##..........##..##..##..##..###.##..##.##..\n" +
                ".####....##..##....##....##..##..#####...######..........#####...######..##.###..####...\n" +
                ".##......##..##....##....##..##..##..##..##..##..........##..##..##..##..##..##..##.##..\n" +
                ".##.......####.....##.....####...##..##..##..##..........#####...##..##..##..##..##..##.\n";

        //PRINT -> WELCOME_MESSAGE
        System.out.println(welcomeMessage);
        System.out.println("Welcome to Futura Bank.");

        //IF CONDITION -> RANDOMIZED DAMAGED CARD CHECKER
        if (damagedCardRandomizer == 5) {
            System.out.println("Your card is damaged. Please contact customer service for a new one.");
            System.out.println("Customer service line: 0488888888");
            System.out.println("Returning your card.");
            System.out.println("...");
            System.out.println("Please take your card.");

            //EXIT -> IF CARD IS DAMAGED
            System.exit(0);
        }


        // ATM -> WHILE_LOOP
        while (true) {
            System.out.println("Please enter your account number: ");

            //SCANNER -> CARD_NUMBER
            accountNumber = scanner.nextLong();

            // RESTRICTION ON CARD NUMBER -> MAX 16 DIGITS
            long checkingDigits = String.valueOf(accountNumber).length();

            // INPUT -> EXPIRATION DATE
            System.out.println("Please fill in the expiration date in the following format: MM/YY ");

            //SCANNER -> EXPIRATION DATE
            expirationDate = scanner.nextInt();

            //BOOLEAN -> WHILE_LOOP
            boolean validation = true;

            //WHILE LOOP -> CARD_NUMBER && EXPIRATION_DATE VALIDATION
            while (validation) {

                // IF_CONDITION -> CARD_NUMBER_LENGTH && EXPIRATION_DATE
                if (checkingDigits != 16 || expirationDate != expirationDateChecker) {
                    System.out.println("The card you've entered is invalid. Please try again.");
                    System.out.println("Please enter your account number: ");

                    //SCANNER -> INPUT
                    accountNumber = scanner.nextLong();
                    System.out.println("Please fill in the expiration date in the following format: MM/YY ");

                    //SCANNER -> INPUT
                    expirationDateChecker = scanner.nextInt();

                    //IF -> account or experation INPUT IS 0
                    if (accountNumber == 0 || expirationDate == 0) {
                        System.out.println("Returning your bank card...");
                        System.out.println("...");
                        System.out.println("Please take your bank card");
                        System.out.println("Thank you for choosing FUTURA Bank... Goodbye !");
                        //EXIT -> IF INPUT == 0
                        System.exit(0);
                    }
                }

                // PRINT -> SUCCESS MESSAGE
                if (accountNumber == cardNumberChecker && expirationDate == expirationDateChecker) {
                    System.out.println("Thank you! The card number is recognized.");
                    validation = false;
                }
            }


            //PRINT -> ENTER PIN
            System.out.println("Please enter your pin: ");

            // SCANNER -> PIN CODE
            int pinCode = scanner.nextInt();
            if (pinCode != pinCodeChecker) {
                logBook[counter] = "Wrong pincode: " + dtf.format(now);
            }

            // FOR_LOOP (PIN_CHECKER)
            for (int index = 0; index <= 3 && pinCode != pinCodeChecker; index++) {

                //IF_INCORRECT_PIN
                if (index == 1) {
                    System.out.println("Incorrect! Please the enter correct pin: ");

                    //SCANNER -> (pinCode)
                    pinCode = scanner.nextInt();

                    //LOGGER -> WRONG PIN_CODE
                    counter = counter + 1;
                    logBook[counter] = "Wrong pincode: " + dtf.format(now);
                }

                //IF_INCORRECT_PIN -> LAST_ATTEMPT
                if (index == 2) {
                    System.err.println("Last try! Please the enter correct pin: ");

                    //SCANNER -> (pinCode)
                    pinCode = scanner.nextInt();

                    //LOGGER -> WRONG PIN_CODE
                    counter = counter + 1;
                    logBook[counter] = "Wrong pincode: " + dtf.format(now);
                }

                //IF_INCORRECT_PIN -> CARD_BLOCKED
                if (index == 3) {
                    System.err.println("Your card has been blocked.");
                    System.out.println("Your card will be retained for further investigation");
                    System.out.println("Recording video-image of the customer");

                    //LOGGER -> CARD_BLOCKED
                    counter = counter + 1;
                    logBook[counter] = "Card blocked: " + dtf.format(now);

                    //EXIT -> IF CARD IS BLOCKED
                    System.exit(0);
                }
            }

            //WHILE LOOP -> boolean
            boolean menuOptions = true;

            //WHILE_LOOP (menuOptions)
            while (menuOptions) {

                System.out.println("Automated Teller Machine");
                System.out.println("Choose 1 for Withdraw");
                System.out.println("Choose 2 for Check Balance");
                System.out.println("Choose 3 for Deposit");
                System.out.println("Choose 4 for EXIT");
                System.out.print("Choose the operation you want to perform:");

                // READ -> SCANNER
                int options = scanner.nextInt();

                // OPTION 1 -> WITHDRAW
                if (options == 1) {
                    // ARRAY -> WITHDRAWAL OPTIONS
                    int[] amounts = {200, 100, 50, 20, 10};
                    int[] withdrawals = new int[amounts.length];

                    //FOR_LOOP -> WITHDRAWAL
                    for (int index = 0; index < amounts.length; index++) {

                        //PRINT -> DAILY LIMIT
                        System.out.println("Please note that the daily limit is 2000 euros");

                        //INPUT -> WITHDRAWAL_AMOUNT
                        System.out.println("Please enter amount of: " + amounts[index] + "euros");

                        //SCANNER -> SCANNER * amounts[index] = WITHDRAWALS[index]
                        withdrawals[index] = scanner.nextInt() * amounts[index];
                    }
                    //DEClARING TOTAL VARIABLE
                    int total = 0;

                    //FOR_LOOP -> TOTAL
                    for (int index = 0; index < withdrawals.length; index++) {
                        total += withdrawals[index];
                    }

                    //LOGGER -> WITHDRAWALS
                    counter = counter + 1;
                    logBook[counter] = "Withdrawn cash: -" + total + " euros " + dtf.format(now);

                    //WITHDRAWN AMOUNT ADDED TO DAILY LIMIT
                    dayLimitChecker = dayLimitChecker + total;

                    // RESTRICTION_ON_AMOUNT_WITHDRAWN
                    if (balance >= total && total <= dailyLimit && dayLimitChecker <= dailyLimit) {

                        // IF CONDITION -> DAILY_HOPPER_LIMIT
                        if (atmHopperRandom >= total) {
                            balance = balance - total;
                            atmHopperRandom = atmHopperRandom - total;

                            // PRINT -> NEW_BALANCE
                            System.out.println("Your new balance is " + balance);
                            System.out.println("You have withdrawn: " + total);

                         //ELSE
                        } else {
                            // PRINT -> DEFAULT_MESSAGE
                            System.out.println(" There is no sufficient amount in the ATM Hopper");
                        }
                    }

                    // DAILY_LIMIT -> DEFAULT_MESSAGE
                    if (total > dailyLimit) {
                        System.out.println("You are exceeding the daily limit of 2000 euros, Please try again.");
                    }

                    // CHECK_ON_SUFFICIENT_FUNDS
                    if (total > balance) {
                        System.out.println("You have insufficient funds to withdraw: " + total);
                        System.out.println("Please contact your bank for further assistance. You can find our opening hours and contact page at www.futurabank.com ");
                    }
                }

                // OPTION 2 -> BALANCE_REVIEW
                if (options == 2) {
                    System.out.println("Your current balance is: " + balance);
                }

                // OPTION 3 -> DEPOSIT
                if (options == 3) {
                    // INPUT -> DEPOSITED_AMOUNT
                    System.out.println("Enter money to be deposited: " + balance);
                    int depositedAmount = scanner.nextInt();

                    // OUTPUT -> NEW_BALANCE
                    balance = balance + depositedAmount;
                    System.out.println("Your Money has been successfully deposited");
                    System.out.println("Your new balance is " + balance);

                    //LOGGER -> DEPOSITS
                    counter = counter + 1;
                    logBook[counter] = "Deposited cash: +" + depositedAmount + " euros " + dtf.format(now);
                }

                // OPTION 4 -> EXIT
                if (options == 4) {
                    // PRINT -> DEFAULT_MESSAGE
                    System.out.println("Returning your bank card...");
                    System.out.println("...");
                    System.out.println("Please take your bank card");
                    System.out.println("Thank you for choosing FUTURA Bank... Goodbye !");
                    break;
                }

                // RESTRICTION_ON_MENU_OPTIONS
                if (options > 4) {
                    System.out.println("Please choose a valid option.");
                }
                // IF OPTION ENTERED IS SMALLER THAT 1 BREAK
                if (options < 1) {
                    break;
                }
            }

            // PRINT -> DEFAULT_MESSAGE
            System.out.println("Returning your bank card...");
            System.out.println("...");
            System.out.println("Please take your bank card");
            System.out.println("Thank you for choosing FUTURA Bank... Goodbye !");
        }
    }
}