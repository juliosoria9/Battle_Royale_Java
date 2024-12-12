package personajes;

public class CreaEnemigos{

	public Enemigo crearEnemigo(String dificultad)
	{
		if(dificultad == "facil")
		{
			return crearEnemigoFacil();
		}else if(dificultad == "media")
		{
			return crearEnemigoMedio();
		}
		
		return crearEnemigoDificil();
	}
	
	//falta pasarles arma
	private Enemigo crearEnemigoFacil()
	{
		return Enemigo(10, arma, "enemigoFacil");
	}

	private Enemigo crearEnemigoMedio()
	{
		return Enemigo(10, arma,"enemigoMedio");
	}
	
	private Enemigo crearEnemigoDificil()
	{
		return Enemigo(10, arma,"enemigoDificil");
	}*/
}
