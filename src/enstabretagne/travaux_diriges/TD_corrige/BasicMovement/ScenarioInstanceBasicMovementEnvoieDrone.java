package enstabretagne.travaux_diriges.TD_corrige.BasicMovement;

import java.util.HashMap;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.monitor.implementation.MovableState;
import enstabretagne.simulation.components.ScenarioId;
import enstabretagne.simulation.core.IScenario;
import enstabretagne.simulation.core.IScenarioInstance;
import enstabretagne.travaux_diriges.TD_corrige.BasicMovement.Scenarios.BasicMvtScenario;
import enstabretagne.travaux_diriges.TD_corrige.BasicMovement.Scenarios.BasicMvtScenarioFeatures;
import enstabretagne.travaux_diriges.TD_corrige.BasicMovement.SimEntity.Bouee.BoueeFeatures;
import enstabretagne.travaux_diriges.TD_corrige.BasicMovement.SimEntity.Bouee.BoueeInit;
import enstabretagne.travaux_diriges.TD_corrige.BasicMovement.SimEntity.Drone.EntityDroneFeature;
import enstabretagne.travaux_diriges.TD_corrige.BasicMovement.SimEntity.Drone.EntityDroneInit;
import enstabretagne.travaux_diriges.TD_corrige.BasicMovement.SimEntity.MouvementSequenceur.EntityMouvementSequenceurFeature;
import enstabretagne.travaux_diriges.TD_corrige.BasicMovement.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;
import enstabretagne.travaux_diriges.TD_corrige.BasicMovement.SimEntity.Navire.EntityNavireFeature;
import enstabretagne.travaux_diriges.TD_corrige.BasicMovement.SimEntity.Navire.EntityNavireInit;
import enstabretagne.travaux_diriges.TD_corrige.BasicMovement.SimEntity.Ocean.EntityOceanFeature;
import enstabretagne.travaux_diriges.TD_corrige.BasicMovement.SimEntity.Ocean.EntityOceanInit;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class ScenarioInstanceBasicMovementEnvoieDrone implements IScenarioInstance {

	@Override

	/*
	 * Ce sc�nariot met en sc�ne un drone devant se d�placer d'un Point � un Point B
	 * suivant les conditions donn�es dans la section 4.3.2.2
	 * 
	 */

	public IScenario getScenarioInstance() {
		BasicMvtScenarioFeatures bsf = new BasicMvtScenarioFeatures("BSF");
		int zPlongee = -10;
		// Cr�ation du drone et des points de passage
		HashMap<String, Point3D> positionsCles = new HashMap<String, Point3D>();
		positionsCles.put("start", new Point3D(0, 0, 0));
		positionsCles.put("plongee", new Point3D(0, 0, zPlongee));

		Point3D A = new Point3D(0, 0, zPlongee);
		Point3D B = new Point3D(100, 100, zPlongee);

		positionsCles.put("A", A);
		positionsCles.put("B", B);
		// ce nombre r�pr�sente les nombres d'oscillation � g�n�rer pour quitter du
		// point A au point B
		int nbPoints = 12;
		
		MovableState mst;
		EntityMouvementSequenceurInit msi;
		EntityMouvementSequenceurFeature feat;

		// Cr�ation du Navire 
		feat = new EntityMouvementSequenceurFeature("MSF");
		positionsCles = new HashMap<String, Point3D>();
		MovableState mstOcean = new MovableState(new Point3D(40,40,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO,
				Point3D.ZERO);
		EntityMouvementSequenceurInit msiOcean = new EntityMouvementSequenceurInit("MSIOCEAN", mstOcean, 0, 0, 0, 0,
				null, 0);
		bsf.getNavires().put(new EntityNavireFeature("O1", 50, 50, Color.RED, feat), new EntityNavireInit("Navire ", msiOcean));
		
		
		// Cr�ation de l'oc�an
		positionsCles = new HashMap<String, Point3D>();
	
	
		bsf.getOcean().put(new EntityOceanFeature("O1"), new EntityOceanInit("Atlantique", msiOcean));

		LogicalDateTime start = new LogicalDateTime("05/12/2017 06:00");
		LogicalDateTime end = start.add(LogicalDuration.ofMinutes(2));
		BasicMvtScenario bms = new BasicMvtScenario(new ScenarioId("S4"), bsf, start, end);

		return bms;
	}

}
