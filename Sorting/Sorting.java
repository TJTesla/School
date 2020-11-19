public class Sortieren
{
	static List<Integer> list = new List<Integer>();
	public static void main (String[] args)
    {
        for(int i = 0; i < 3; i++) {
			list.clear();
			for(int k = 0; k < 7; k++) {
				list.append((int) (Math.random()* 300) + 1);
			}
			list.toFirst();
			System.out.print("Unsortiert: ");
			while(list.hasAccess()) {
				System.out.print(list.getContent() + "  ");
				list.next();
			}
			System.out.println();
			if(i == 0) {
				quicksort(list);
			} else if(i == 1) {
				insertionsort(list);
			} else {
				selectionsort(list);
			}
			list.toFirst();
			System.out.print("Sortiert: ");
			while(list.hasAccess()) {
				System.out.println(list.getContent());
				list.next();
			}
			System.out.println();
			System.out.println();
        }
    }
	
    public static void quicksort(List<Integer> list){
        if(list.length() > 1){
            List<Integer> klein = new List<Integer>();
            List<Integer> groß = new List<Integer>();
			list.toFirst();
            int pivot = list.getContent();
            list.remove();
            while(!list.isEmpty()) {
				list.toFirst();
				int i = list.getContent();
				if(i < pivot) {
					klein.append(i);
				} else {
					groß.append(i);
				}
				list.remove();
            }
            quicksort(klein);
            quicksort(groß);
            
            list.concat(klein);
            list.append(pivot);
            list.concat(groß);
        }
    }
    
    public static void insertionsort(List<Integer> list) {
		List<Integer> speicher = new List<Integer>();
		while(!list.isEmpty()) {
			list.toFirst();
			int i = list.getContent();
			speicher.toFirst();
			while(speicher.hasAccess() && i > speicher.getContent()) {
				speicher.next();
			}
			if(speicher.hasAccess()) {
				speicher.insert(i);
			} else {
				speicher.append(i);
			}
			list.remove();
		}
		list.concat(speicher);
    }
    
    public static void selectionsort(List<Integer> liste) {
    		List<Integer> speicher = new List<Integer>();
	    	while(!liste.isEmpty()) {
	    		liste.toFirst();
	    		int i = liste.getContent();
	    		liste.next();
	    		while(liste.hasAccess()) {
	    			if(liste.getContent() < i) {
	    				i = liste.getContent();
	    			}
	    			liste.next();
	    		}
	    		liste.toFirst();
	    		while(liste.hasAccess()) {
	    			if(liste.getContent() == i) {
	    				liste.remove();
	    				break;
	    			}
	    			liste.next();
	    		}
	    		speicher.append(i);
	    	}
	    	liste.concat(speicher);
	}
}
