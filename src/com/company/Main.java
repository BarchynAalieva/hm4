package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 1300;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {260, 250, 240, 200, 280, 220, 210, 215};
    public static int[] heroesDamage = {20, 25, 15, 0, 5, 10, 20, 22};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic",
            "Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static int kRound = 0;
    public static boolean blockBossAttacks = false;

    public static void main(String[] args) {
        printStatisrics();
        while (!isFinished()) {
            round();

        }
    }

    private static void round() {
        System.out.println("Round" + kRound++);
        if (bossHealth > 0) {
            tor();
            bossHit();
            heroesHit();
            if (heroesHealth[1] != 0){
                medical();
            }
            lucky();


        }
        printStatisrics();
    }

    private static void heroesHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0){
                if(bossHealth - heroesDamage[i] < 0){
                    bossHealth = 0;
                }
                else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

    private static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int value : heroesHealth) {
            if (value > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead)
            System.out.println("Boss won!!!");
        return allHeroesDead;

    }

    private static void bossHit() {
        Random random = new Random();
        int k = random.nextInt(3);
        int chance = random.nextInt(3);

        if(chance ==2){
            System.out.println("Boss Angry");
        }
        for (int i = 0; i <heroesHealth.length ; i++) {
            if (heroesHealth[i] > 0)  {
                if(chance ==2){
                    if(heroesHealth[i] - bossDamage * k < 0){
                        heroesHealth[i] = 0;
                    }
                    else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage * k;
                    }
                }else {
                    if(heroesHealth[i] - bossDamage < 0){
                        heroesHealth[i] = 0;
                    }
                    else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                    }
                }
            }
        }
    }

    private static void medical(){
        Random random = new Random();
        int kHeal = random.nextInt(30)+ 5;
        int min = heroesHealth[0];
        int index = 0;
        for (int i = 0; i < heroesHealth.length ; i++) {
            if (heroesHealth[i] < min){
                min = heroesHealth[i];
                index = i;
            }
        }

        if (heroesHealth[index] < 100 && heroesHealth[index] != 0){
            heroesHealth[index] += kHeal;
            System.out.println("Medic Heal " + heroesAttackType[index]+" for " +kHeal);
        }
    }

    private static void lucky(){
        Random random = new Random();
        int chance = random.nextInt(3);
        if (chance ==2){
            if (heroesHealth[5] != 0){
                heroesHealth[5] += bossDamage;
                System.out.println("Lucky");
            }
        }
    }

    private static void tor(){
        Random random = new Random();
        int chance = random.nextInt(3);
        if (chance ==1 && heroesHealth[7] != 0){
            bossDamage = 0;
            System.out.println("Boss Stan");
        }
        else{
            bossDamage =50;
        }
    }

    private static void printStatisrics() {
        System.out.println("_________________________");
        System.out.println("Boss health " + bossHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health " + heroesHealth[i]);
        }
        System.out.println("_________________________");
    }
}
