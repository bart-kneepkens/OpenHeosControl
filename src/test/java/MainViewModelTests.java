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

/**
 *
 * @author bartkneepkens
 */

import Gui.Observers.ObservablePropertyNames;
import ViewModel.MainViewModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class MockObserver implements PropertyChangeListener {
    Object object;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.object = evt.getNewValue();
    }
    
}

public class MainViewModelTests {
    
    MainViewModel viewModel;
    
    public MainViewModelTests() {
        viewModel = new MainViewModel();
    }
    
    @Test
    public void testChangeVolume() {
        MockObserver obs = new MockObserver();
        viewModel.addObserverForProperty(ObservablePropertyNames.VOLUME, obs);
        viewModel.volumeUpdated(99);
        assertEquals((int)obs.object, 99);
    }
}
