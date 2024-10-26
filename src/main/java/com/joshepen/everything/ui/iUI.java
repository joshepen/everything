package com.joshepen.everything.ui;

import com.joshepen.everything.logic.DirectoryHandler;
import com.joshepen.everything.objects.DisplayData;

public interface iUI {
    public void setResults(DisplayData data);
    public void setDirectoryHandler(DirectoryHandler directoryHandler);
}
