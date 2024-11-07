package logic;

import java.util.ArrayList;

import com.mysql.cj.util.StringUtils;

import data.ChallengeDB;
import entities.Challenge;

public class ChallengeLogic {
	
	ChallengeDB challengeDB = new ChallengeDB();

	public ArrayList<Challenge> getAll() {
		return challengeDB.getAll();
	}

	public Challenge getById(Challenge c) {
		return challengeDB.getById(c);
	}

	public void delete(Challenge c) {
		challengeDB.delete(c);
	}

	public Challenge validateAttributes(Challenge c) {
		if (c.getTopic() != null
				&& !c.getTopic().isDeleted()
				&& !StringUtils.isEmptyOrWhitespaceOnly(c.getNameChallenge())
				&& c.getAmountQuestions() >= 0 && c.getIdChallenge() >= 0) {
			return c;
		}
		return null;
	}

	public void update(Challenge c) {
		challengeDB.update(c);
		
	}

	public void save(Challenge c) {
		challengeDB.save(c);
	}
}
