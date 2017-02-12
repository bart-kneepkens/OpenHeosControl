/*
 * Copyright (C) 2017 bartkneepkens
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Connection;

/**
 *  Interface that is used to subscribing to change notifications that are published by the Heos system.
 * @author bartkneepkens
 */
public interface IChangeListener {
    void playerStateChanged(String state);
    void playerVolumeChanged(int level);
    void playerNowPlayingChanged(String nowPlaying);
    void playerNowPlayingProgress(int current, int duration);
}
