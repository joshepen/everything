package com.joshepen.everything.ui;

import com.joshepen.everything.objects.SearchResult;
import java.util.Collection;

interface iUI {
    public void setResults(Collection<? extends SearchResult> data);
}
