package model;

public class PlayersModel {
	private String Name;
	private String Position;
	private String Nation;
	private String Age;
	private String Height;
	private String Weight;
	private String PreferredFoot;
	private String SquadNumber;

	public PlayersModel() {
		super();
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public String getNation() {
		return Nation;
	}

	public void setNation(String nation) {
		Nation = nation;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getHeight() {
		return Height;
	}

	public void setHeight(String height) {
		Height = height;
	}

	public String getWeight() {
		return Weight;
	}

	public void setWeight(String weight) {
		Weight = weight;
	}

	public String getPreferredFoot() {
		return PreferredFoot;
	}

	public void setPreferredFoot(String preferredFoot) {
		PreferredFoot = preferredFoot;
	}

	public String getSquadNumber() {
		return SquadNumber;
	}

	public void setSquadNumber(String squadNumber) {
		SquadNumber = squadNumber;
	}

	/**
	 * @param name
	 * @param position
	 * @param nation
	 * @param age
	 * @param height
	 * @param weight
	 * @param preferredFoot
	 * @param squadNumber
	 */
	public PlayersModel(String Name, String Position, String Nation, String Age, String Height, String Weight,
			String PreferredFoot, String SquadNumber) {
		super();
		this.Name = Name;
		this.Position = Position;
		this.Nation = Nation;
		this.Age = Age;
		this.Height = Height;
		this.Weight = Weight;
		this.PreferredFoot = PreferredFoot;
		this.SquadNumber = SquadNumber;
	}

}
