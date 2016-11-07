/**
 * 
 * @author Konrad Sobolewski 3AR 
 *
 */
public class Start
{
	
	/**
	 * 
	 * @param win - view
	 * @param model - model
	 * @param conroller - controller
	 * 
	 * przekazuje model i controller do jednej z klas odp. za odœwie¿anie obrazu
	 */
	public static void main(String[] args)
	{	
		Model model=new Model(1200,600);
		
		View win=new View(1200,600);
		
		Controller controller=new Controller (win.getAPanel(),model);
		
		win.getAPanel().setModel(model,controller);
	}

}
