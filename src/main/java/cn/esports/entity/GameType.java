package cn.esports.entity;

import java.util.List;

public class GameType {
	/**
	 * code : 200 t : {"gameList":[{"id":1,"name":"王者荣耀","shortName":"King of
	 * Glory"},{"id":2,"name":"英雄联盟","shortName":"League of
	 * Legends"},{"id":3,"name":"绝地求生","shortName":"PLAYERUNKNOWN\u2019S
	 * BATTLEGROUNDS"}]}
	 */

	private int code;
	private TBean t;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public TBean getT() {
		return t;
	}

	public void setT(TBean t) {
		this.t = t;
	}

	public static class TBean {
		private List<GameListBean> gameList;

		public List<GameListBean> getGameList() {
			return gameList;
		}

		public void setGameList(List<GameListBean> gameList) {
			this.gameList = gameList;
		}

		public static class GameListBean {
			/**
			 * id : 1 name : 王者荣耀  2 英雄联盟  3绝地求生shortName : King of Glory
			 */

			private int id;
			private String name;
			private String shortName;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getShortName() {
				return shortName;
			}

			public void setShortName(String shortName) {
				this.shortName = shortName;
			}
		}
	}
}
