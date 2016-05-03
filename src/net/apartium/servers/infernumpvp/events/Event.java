package net.apartium.servers.infernumpvp.events;

public abstract class Event {
	protected String[] args;
	protected String name;
	
	private EventOptions options;
	
	public Event(String[] args, String name) {
		this.args=args;
		this.name=name;
		this.options=new EventOptions(this);
		
	}
	
	public final EventOptions options() {return options;}
	
	public abstract void onStart();
	
	public abstract void onWin();
	
	public static class EventOptions {
		private Event e;
		
		public int moneyPrize=0;
		public int minPlayers=0; //0 for no limit
		public int maxPlayers=0; //0 for no limit
		private EventOptions(Event e) {
			this.e=e;
		}
		
		public Event getEvent() {
			return e;
		}
		
	}
}
