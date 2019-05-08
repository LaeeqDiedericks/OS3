//package molecule;

public class Carbon extends Thread {
	
	private static int carbonCounter =0;
	private int id;
	private Methane sharedMethane;
	
	public Carbon(Methane methane_obj) {
		Carbon.carbonCounter+=1;
		id=carbonCounter;
		this.sharedMethane = methane_obj;
	}
	
	public void run() {
	    try {
		sharedMethane.mutex.acquire();
		sharedMethane.addCarbon();
		if(sharedMethane.getHydrogen() >= 4){
			System.out.println("---Group ready for bonding---");
			sharedMethane.hydrogensQ.release(4);
			sharedMethane.removeHydrogen(4);
			sharedMethane.carbonQ.release();
			sharedMethane.removeCarbon(1);	 
	    	 	// you will need to fix below
		}
		else{
			sharedMethane.mutex.release();
		}
		sharedMethane.carbonQ.acquire();	
	    	sharedMethane.bond("C"+ this.id);  //bond
		sharedMethane.barrier.b_wait();
		sharedMethane.mutex.release();
	    		   	 
	    }
	    catch (InterruptedException ex) { /* not handling this  */}
	   // System.out.println(" ");
	}

}
