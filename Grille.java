package GrilleImpl;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos Philippe
 */
public class Grille {

    /**
     * affiche la grille
     *
     * @param grille tableau à deux dimension (9*9) representant la grille de
     * sudoku
     */
    public void affichage(int[][] grille) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (((j+1) % 3) == 0) {
                    System.out.print(grille[i][j]+ " | " );
                } else {
                    System.out.print(grille[i][j]+ " " );
                }
            }
            System.out.println();
            if (((i+1) % 3) == 0) {
                System.out.println("------------------------");
            }
        }
        System.out.println();
        System.out.println();
    }

    /**
     * recherche une valeur sur une ligne de la grille
     *
     * @param k entier à rechercher sur la grille
     * @param grille tableau à deux dimension (9*9) representant la grille de
     * sudoku
     * @param i indice de la ligne à tester
     * @return false si la valeur est présente et true au cas contraire
     */
    public boolean absentSurLigne(int k, int[][] grille, int i) {
        for (int j = 0; j < 9; j++) {
            if (grille[i][j] == k) {
                return false;
            }
        }
        return true;
    }

    /**
     * recherche une valeur sur une colonne de la grille
     *
     * @param k entier à rechercher sur la grille
     * @param grille tableau à deux dimension (9*9) representant la grille de
     * sudoku
     * @param j indice de la colonne à tester
     * @return false si la valeur est présente et true au cas contraire
     */
    public boolean absentSurColonne(int k, int[][] grille, int j) {
        for (int i = 0; i < 9; i++) {
            if (grille[i][j] == k) {
                return false;
            }
        }
        return true;
    }

    /**
     * recherche une valeur sur une region de la grille
     *
     * @param k entier à rechercher sur la grille
     * @param grille tableau à deux dimension (9*9) representant la grille de
     * sudoku
     * @param i indice de la ligne à tester
     * @param j indice de la colonne à tester
     * @return false si la valeur est présente et true au cas contraire
     */
    public boolean absentSurBloc(int k, int[][] grille, int i, int j) {
        int u = i - (i % 3);
        int v = j - (j % 3);
        for (i = u; i < u + 3; i++) {
            for (j = v; j < v + 3; j++) {
                if (grille[i][j] == k) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Test si une valeur est un choix possible et actualise la grille
     *
     * @param grille tableau à deux dimension (9*9) representant la grille de
     * sudoku
     * @param position
     * @return true si la grille a été actualiser et false si non
     */
    public boolean estValide(int[][] grille, int position) {
        if (position == 9 * 9) {
            return true;
        }

        int i = position / 9;
        int j = position % 9;

        if (grille[i][j] != 0) {
            return estValide(grille, position + 1);
        }

        for (int k = 1; k <= 9; k++) {
            if (absentSurLigne(k, grille, i) && absentSurColonne(k, grille, j)
                    && absentSurBloc(k, grille, i, j)) {
                grille[i][j] = k;

                if (estValide(grille, position + 1)) {
                    return true;
                }
            }
        }
        grille[i][j] = 0;
        return false;
    }

    public static void main(String[] Args) {
        int[][] grille
                = {
                    {9, 0, 0, 1, 0, 0, 0, 0, 5},
                    {0, 0, 5, 0, 9, 0, 2, 0, 1},
                    {8, 0, 0, 0, 4, 0, 0, 0, 0},
                    {0, 0, 0, 0, 8, 0, 0, 0, 0},
                    {0, 0, 0, 7, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 2, 6, 0, 0, 9},
                    {2, 0, 0, 3, 0, 0, 0, 0, 6},
                    {0, 0, 0, 2, 0, 0, 9, 0, 0},
                    {0, 0, 1, 9, 0, 4, 5, 7, 0}
                };
        
        Grille s = new Grille();
        System.out.println("Affichage avant");       
        s.affichage(grille);
        
        s.estValide(grille,0);
        
        System.out.println("Affichage avant");
        s.affichage(grille);
        
    }
}
