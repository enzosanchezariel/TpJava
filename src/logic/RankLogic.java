package logic;

import java.util.ArrayList;

import com.mysql.cj.util.StringUtils;

import data.RankDB;
import entities.Rank;
import entities.User;

public class RankLogic {
	
	RankDB rankDB = new RankDB();

	public Rank getById(Rank r) {
		return rankDB.getById(r);
	}

	public ArrayList<Rank> getAll() {
		return rankDB.getAll();
	}

	public void delete(Rank r) {
		rankDB.delete(r);
	}

	public Rank validateAttributes(Rank r) {
		if (r.getId() >= 0
				&& r.getAmountChallenges() >= 0
				&& !StringUtils.isEmptyOrWhitespaceOnly(r.getName())) {
			return r;
		}
		return null;
	}

	public void update(Rank r) {
		rankDB.update(r);
	}

	public void save(Rank r) {
		rankDB.save(r);
	}

	public Rank getRankByUserId(User u) {
		return rankDB.getRankByUserId(u);
	}
}
