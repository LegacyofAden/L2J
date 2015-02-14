/*
 * Copyright (C) 2013-2015 Joxit
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Analyzer for files like item_pch, quest_pch...
 *
 * [name] = id
 *
 * @author Joxit
 *
 */
public class PchFinder {

	/**
	 * Give the name of something by his id.
	 *
	 * @param path of pch
	 * @param id in pch
	 * @return name which has this id (name found in *_pch)
	 * @throws IOException
	 */
	public static String getNameById(final String path, final String id) throws IOException {
		final ArrayList<String> res = getNamesByIds(path, id);
		return res.stream().findFirst().orElse(null);
	}

	/**
	 * Give all names in pch by their ids
	 *
	 * @param path of pch
	 * @param ids in pch
	 * @return names which has these ids (names found in *_pch)
	 * @throws IOException
	 */
	public static ArrayList<String> getNamesByIds(final String path, final String... ids)
			throws IOException {
		return getNamesByIds(path, Arrays.asList(ids));
	}

	/**
	 * Give all names in pch by their ids
	 *
	 * @param path of pch
	 * @param ids in pch
	 * @return names which has these ids (names found in *_pch)
	 * @throws IOException
	 */
	public static ArrayList<String> getNamesByIds(final String path, final Collection<String> ids)
			throws IOException {
		final BufferedReader r = new BufferedReader(new FileReader(path));
		final ArrayList<String> res = new ArrayList<String>();
		while (r.ready()) {
			final String line = r.readLine();
			final String[] column = line.replaceAll("[^\\w\\d\\]]", "").split("]");
			if (column.length == 2) {
				if (ids.contains(column[1])) {
					res.add(column[0]);
				}
			}
		}
		r.close();

		return res;
	}

	/**
	 * Give the name of something by his id.
	 *
	 * @param path of pch
	 * @param name in pch
	 * @return id which has this name (name found in *_pch)
	 * @throws IOException
	 */
	public static String getIdByName(final String path, final String name) throws IOException {
		final ArrayList<String> res = getIdsByNames(path, name);
		return res.stream().findFirst().orElse(null);
	}

	/**
	 * Give all names in pch by their ids
	 *
	 * @param path of pch
	 * @param names in pch
	 * @return ids which has these names (name found in *_pch)
	 * @throws IOException
	 */
	public static ArrayList<String> getIdsByNames(final String path, final String... names)
			throws IOException {
		return getIdsByNames(path, Arrays.asList(names));
	}

	/**
	 * Give all names in pch by their ids
	 *
	 * @param path of pch
	 * @param names in pch
	 * @return ids which has these names (name found in *_pch)
	 * @throws IOException
	 */
	public static ArrayList<String> getIdsByNames(final String path, final Collection<String> names)
			throws IOException {
		final BufferedReader r = new BufferedReader(new FileReader(path));
		final ArrayList<String> res = new ArrayList<String>();
		while (r.ready()) {
			final String line = r.readLine();
			final String[] column = line.replaceAll("[^\\w\\d\\]]", "").split("]");
			if (column.length == 2) {
				if (names.contains(column[0])) {
					res.add(column[1]);
				}
			}
		}
		r.close();

		return res;
	}

	/**
	 * Print all the npc IDs in relationship with this quest name.
	 *
	 * @param path of pch
	 * @param namesOrIds
	 * @throws IOException
	 */
	public static void printNamesAndIds(final String path, final String... namesOrIds)
			throws IOException {
		printNamesAndIds(path, Arrays.asList(namesOrIds));
	}

	/**
	 * Print all the npc IDs in relationship with this quest name.
	 *
	 * @param path of pch
	 * @param namesOrIds
	 * @throws IOException
	 */
	public static void printNamesAndIds(final String path, final Collection<String> namesOrIds)
			throws IOException {
		final BufferedReader r = new BufferedReader(new FileReader(path));
		/* on affiche l'id de tous les npcs */
		while (r.ready()) {
			final String line = r.readLine();
			final String[] column = line.replaceAll("[^\\w\\d\\]]", "").split("]");
			if (column.length == 2) {
				if (namesOrIds.contains(column[0]) || namesOrIds.contains(column[1])) {
					System.out.println(column[0] + " = " + column[1]);
				}
			}
		}
		r.close();
	}

}
