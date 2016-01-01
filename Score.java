package projet_bulles;

import java.io.File;

public class Score {
	/*
	* Un score représente de nombre de clics du joueur sur le nombre de bulles du pallier
	* Dans le fichier texte, les scores sont au format suivant |nombre_bulles-nombre_clics|
	* Exemple : |12-7|
	* Chaque score est séparé par un |
	* Exemple |12-6|23-7|
	* Le | sépare les scores entre eux, le - sépare le nombre_bulles du nombre_clics
	*/
	
	private int nombre_clics; //Nombre de clics du score
	private int nombre_bulles; //Nombre de bulles du score
	private static File fichier = new File("D:\\utilisateurs.txt");
	private String[][] scores;

	/*
	 * Constructeur à vide, le score est de 0 clic - 0 bulle
	 */
	public Score() {
		this.nombre_clics = 0; //Initialisation du nombre de clic à 0
		this.nombre_bulles = 0; //Initialisation du nombre de bulles à 0
	}

	/*
	 * Décompose le score de la ligne passé en paramètre enlève cette
	 * information de cette dernière afin de "l'alléger", moins lourde pour les
	 * prochains traitement dessus
	 */
	public void recupScore(StringBuilder ligne) {
		// ligne est un string qui représente tout les scores de l'utilisateur tel qu'il sont dans le fichier texte
		// On va a certain moment tronquer cette variable pour faciliter l'analyse de cette variable les prochaines fois
		ligne.delete(0, 1); //Le premier élement de la ligne est le |, on le supprime 
		int i = 0; //On initialise la variable qui va parcourire la ligne
		StringBuilder nombre_clics = new StringBuilder(); //Le nombre de clics sera dans un premier temps sous forme d'un StringBuilder
		StringBuilder nombre_bulles = new StringBuilder(); //Le nombre de bulles sera dans un premier temps sous forme d'un StringBuilder
		while (ligne.charAt(i) != '-') { //Tant qu'on a pas atteinte le séparateur -, on lit le nombre de clics
			nombre_clics.append(ligne.charAt(i)); //On l'ajoute à la variable temporaire contenante le nombre de clics
			i++; //On incrémente la variable qui parcoure la ligne
		}
		i++; //On incrémente pour passer le - (voir format d'un score dans le fichier texte)
		while (ligne.charAt(i) != '|') { //Même chose qu'avec le nombre de clics
			nombre_bulles.append(ligne.charAt(i));
			i++;
		}
		this.nombre_clics = Integer.parseInt(nombre_clics.toString()); //On attribut le nombre de clics au score à partire de la variable, il faut transformer le String en int
		this.nombre_bulles = Integer.parseInt(nombre_bulles.toString()); //Même chose
		ligne.delete(0, i); //On supprime la sous-chaine de caractere lu pour faciliter  la lecture  du prochain score
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString() toString() classic
	 * Cette méthode n'est pas utilisé dans le programme final, simplement faite pour les tests / première ittération
	 */
	public String toString() {
		StringBuilder score = new StringBuilder();
		score.append("clics : ");
		switch (String.valueOf(this.nombre_clics).length()) {
		//Pour que l'affichage soit "propre", on regarde la taille du nombre de clics, et on s'arrange pour
		// que même si la taille n'est pas la même entre chaque score 
		// Exemple : |12-7|123-8|, l'affichage restera aligné dans l'invite de commande
		case 1:
			score.append(this.nombre_clics + "  ");
			break;
		case 2:
			score.append(this.nombre_clics + " ");
			break;
		case 3:
			score.append(this.nombre_clics);
			break;
		}
		score.append(" - "); //On rajoute le séparateur
		switch (String.valueOf(this.nombre_bulles).length()) {
		//Même chose qu'avec le nombre de clics
		case 1:
			score.append("  " + this.nombre_bulles);
			break;
		case 2:
			score.append(" " + this.nombre_bulles);
			break;
		case 3:
			score.append(this.nombre_bulles);
			break;
		}
		score.append(" : bulles");
		return score.toString();
	}

	/*
	 * Getter nombre_clics d'un score (this)
	 */
	public int getnombre_clics() {
		return this.nombre_clics;
	}

	/*
	 * Getter nombre_bulles d'un score (this)
	 */
	public int getnombre_bulles() {
		return this.nombre_bulles;
	}

	public static void main(String[] Args) {
		Score s1 = new Score();
		Score s2 = new Score();
		Score s3 = new Score();
		System.out.println(s1.toString());
		StringBuilder str = new StringBuilder();
		str.append("|4-1|12-145|4-13|||||");
		s1.recupScore(str);
		s2.recupScore(str);
		s3.recupScore(str);
		System.out.println(s1.toString());
		System.out.println(s2.toString());
		System.out.println(s3.toString());
	}
}
