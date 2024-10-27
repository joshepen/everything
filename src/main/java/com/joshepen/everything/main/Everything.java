/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.joshepen.everything.main;
import com.joshepen.everything.ui.*;
import com.joshepen.everything.objects.*;
import com.joshepen.everything.logic.*;

/**
 *
 * @author joshu
 */
public class Everything {

    public static void main(String[] args) {
        iUI ui = new UI();
        DirectoryHandler directoryHandler = new DirectoryHandler(ui);
        ui.setDirectoryHandler(directoryHandler);
    }
}
