import java.util.Random;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) throws InterruptedException {
        //System objects
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
        //Game variables
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;
        int deadEnemyCount = 0;
        int savedTownsPeopleCount = 0;
        //Player variables
        int health = 100;
        int attackDamage = 50;
        int numHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50;  //Percentage

        boolean gameRunning = true;

        System.out.println("Here your adventure starts");

        GAME:
        //to iterate the game back here
        while (gameRunning) {
            System.out.println("-------------------------------------------------------------------------------------");
            int enemyHealth = rand.nextInt(maxEnemyHealth);   //Health will be randomized between 0 and maxEnemy Health for every iteration
            String enemy = enemies[rand.nextInt(enemies.length)]; //0 ile array uzunluğu arasında random.
            System.out.println("\t# " + enemy + " has appeared! #\n"); //# Skeleton has appeared #

            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1 Attack");
                System.out.println("\t2 Drink HP Potion");
                System.out.println("\t3 Go to another room");

                String input = scan.nextLine();
                if (input.equals("1")) {
                    int damageDealt = rand.nextInt(attackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);

                    enemyHealth -= damageDealt;
                    health -= damageTaken;

                    System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage");
                    System.out.println("\t> You received " + damageTaken + " damage in retaliation");
                    if (damageTaken == 0) {
                        System.out.println("\t> Enemy has missed its attack");
                    }
                    if (damageDealt == 0) {
                        System.out.println("\t> You missed your attack");
                    }
                    Thread.sleep(2000);

                    if (health < 1) {
                        System.out.println("\t> You died. Game Over");
                        break;
                    }
                } else if (input.equals("2")) {
                    if (health == 100) {
                        System.out.println("\t You are at max health");
                        Thread.sleep(3000);
                    } else if (numHealthPotions > 0) {
                        if (health + healthPotionHealAmount > 100) {
                            numHealthPotions--;
                            System.out.println("\t> You drank a health potion, it healed you for " + (100 - health)
                                    + " HP" + "\n\t> You now have " + 100 + " HP."
                                    + "\n\t> You have " + numHealthPotions + " health potions left. \n");
                            health = 100;
                            Thread.sleep(500);
                        } else if (health + healthPotionHealAmount < 100) {
                            health += healthPotionHealAmount;
                            numHealthPotions--;
                            System.out.println("\t> You drank a health potion, it healed you for " + healthPotionHealAmount
                                    + " HP" + "\n\t> You now have " + health + " HP."
                                    + "\n\t> You have " + numHealthPotions + " health potions left. \n");
                            Thread.sleep(2000);

                        }
                    } else {
                        System.out.println("\t>You are out of health potions!" +
                                "\n\t>Defeat an enemy for a chance to drop a health potion");
                        Thread.sleep(3000);
                    }
                } else if (input.equals("3")) {
                    System.out.println("\tYou went to another room escaping from " + enemy + "!");
                    Thread.sleep(3000);
                    continue GAME;       // Iterates from GAME
                } else {
                    System.out.println("\t Invalid command");
                }
            }
            if (health < 1) {
                System.out.println("You died. Game Over");
                break;
            }
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            deadEnemyCount++;
            System.out.println(" # You have " + health + " HP left. #");
            System.out.println(" # You have saved a townspeople from the hands of the evil. #");
            savedTownsPeopleCount++;
            System.out.println(" # Townspeople have gave you some food. #");
            int townsPeopleHealAmount = (rand.nextInt(100));
            if (health + townsPeopleHealAmount > 100) {
                System.out.println("\t> Food you ate healed you for " + (100 - health)
                        + " HP" + "\n\t> You now have " + 100 + " HP.");
                health = 100;
                Thread.sleep(2000);
            } else if (health + healthPotionHealAmount < 100) {
                health += townsPeopleHealAmount;
                System.out.println("\t> You ate the food townspeople gave you, it healed you for " + townsPeopleHealAmount
                        + " HP" + "\n\t> You now have " + health + " HP.");
                Thread.sleep(2000);

            }
            if (rand.nextInt(100) < healthPotionDropChance) { //if the random number is smaller than the healthpotiondropchance
                numHealthPotions++;
                System.out.println(" # The " + enemy + " dropped a health potion! # ");
                System.out.println(" # You now have " + numHealthPotions + " HP Potions! # ");
            }
            Thread.sleep(500);
            System.out.println(" # You saw a new enemy ahead #");
            Thread.sleep(2000);
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("1. Continue fighting");
        System.out.println("2. Exit the dungeon");

        String input = scan.nextLine();

        while (!input.equals("1") && !input.equals("2")) {
            System.out.println("Invalid command");
            input = scan.nextLine();
            if (input.equals("1")) {
                System.out.println("You contine on your adventure!.");
            } else if (input.equals("2")) {
                System.out.println("You exit the dungeon");
                System.out.println("You killed " + deadEnemyCount + " enemies");
                System.out.println("You saved " + savedTownsPeopleCount + " people");
                break;
            }
        }
        System.out.println("############################");
        System.out.println("# Thanks for playing! #");
        System.out.println("############################");
    }
}
