/*
 * ModeShape (http://www.modeshape.org)
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.
 *
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * See the AUTHORS.txt file in the distribution for a full listing of
 * individual contributors.
 */
package org.jboss.tools.modeshape.rest.views;

import static org.jboss.tools.modeshape.rest.IUiConstants.ModeShape_IMAGE_16x;
import org.eclipse.core.resources.IFile;
import org.eclipse.debug.ui.console.FileLink;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IRegion;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.AbstractConsole;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.jboss.tools.modeshape.rest.Activator;
import org.jboss.tools.modeshape.rest.RestClientI18n;
import org.modeshape.common.util.CheckArg;
import org.modeshape.web.jcr.rest.client.Status;
import org.modeshape.web.jcr.rest.client.Status.Severity;

/**
 * The <code>PublishingMessageConsole</code> is a message console view where status of publishing operations are logged. This class
 * ensures all writes to the console are done in the UI thread.
 */
public final class PublishingMessageConsole extends MessageConsole {

    // =======================================================================================================================
    // Constants
    // =======================================================================================================================

    /**
     * Start tag for adding emphasis to a message. Tag will appear in a properties file.
     */
    private static final String EMPHASIS_START_TAG = "<em>";

    /**
     * End tag for adding emphasis to a message. Tag will appear in a properties file.
     */
    private static final String EMPHASIS_END_TAG = "</em>";

    /**
     * The identifier and type of the Message Console.
     */
    private static final String ID = "org.jboss.tools.modeshape.rest.views.PublishingMessageConsole";

    /**
     * The message console name.
     */
    private static final String NAME = RestClientI18n.publishingConsoleName.text();

    // =======================================================================================================================
    // Class Methods
    // =======================================================================================================================

    /**
     * Note: The <code>PublishingMessageConsole</code> should <strong>NOT</strong> be cached as the user can open/close/create instances.
     * 
     * @return the Message Console if available or a new one (never <code>null</code>)
     */
    private static PublishingMessageConsole getMessageConsole() {
        PublishingMessageConsole console = null;
        IConsoleManager consoleMgr = ConsolePlugin.getDefault().getConsoleManager();
        IConsole[] consoles = consoleMgr.getConsoles();

        // see if console is open
        for (int i = 0; i < consoles.length; ++i) {
            if (NAME.equals(consoles[i].getName())) {
                console = (PublishingMessageConsole)consoles[i];
                break;
            }
        }

        // create console if necessary
        if (console == null) {
            console = new PublishingMessageConsole();
            consoleMgr.addConsoles(new IConsole[] {console});
        }

        return console;
    }

    /**
     * Adds a line feed to the console after the message is printed.
     * 
     * @param message the message being written to the console (never <code>null</code>)
     */
    public static void writeln( String message ) {
        CheckArg.isNotNull(message, "message");
        writeln(message, null);
    }

    /**
     * Adds a line feed to the console after the message is printed.
     * 
     * @param message the message being written to the console (never <code>null</code>)
     * @param file the file whose full path, which is contained in the message, will be made into a hyperlink (may be
     *        <code>null</code>)
     */
    public static void writeln( String message,
                                IFile file ) {
        CheckArg.isNotNull(message, "message");

        PublishingMessageConsole console = getMessageConsole();
        console.print(message, true, file);
    }

    // =======================================================================================================================
    // Constructors
    // =======================================================================================================================

    /**
     * Prevent construction.
     */
    private PublishingMessageConsole() {
        super(NAME, Activator.getDefault().getImageDescriptor(ModeShape_IMAGE_16x));
    }

    // =======================================================================================================================
    // Methods
    // =======================================================================================================================

    /**
     * @param message the message being searched for
     * @param file the file whose full path appears in the message and will become a hyperlink
     */
    void addDocumentListener( String message,
                              IFile file ) {
        getDocument().addDocumentListener(new HyperlinkCreator(message, this, file));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.console.AbstractConsole#getType()
     */
    @Override
    public String getType() {
        return ID;
    }

    /**
     * @param message the message being written to the console (never <code>null</code>)
     * @param doLineFeedAtEnd a flag indicating if a line feed should be done after writing the message
     * @param file the file to create a hyperlink for (may be <code>null</code>)
     */
    private void print( final String message,
                        final boolean doLineFeedAtEnd,
                        final IFile file ) {
        assert (message != null);
        final Display display = Display.getDefault();
        final AbstractConsole console = this;

        if (!display.isDisposed()) {
            display.asyncExec(new Runnable() {
                /**
                 * {@inheritDoc}
                 * 
                 * @see java.lang.Runnable#run()
                 */
                @Override
                public void run() {
                    if (!display.isDisposed()) {
                        // bring focus to this view
                        console.activate();

                        // register document listener before writing to console
                        if (file != null) {
                            addDocumentListener(message, file);
                        }

                        MessageConsoleStream stream = newMessageStream();

                        for (int beginIndex = 0, endIndex = 0, msgLength = message.length(); endIndex < msgLength;) {
                            int startTagIndex = message.indexOf(EMPHASIS_START_TAG, beginIndex);
                            int endTagIndex = ((startTagIndex < 0) ? -1 : message.indexOf(EMPHASIS_END_TAG,
                                                                                         startTagIndex
                                                                                         + EMPHASIS_START_TAG.length()));

                            // ignore tags if both tags are not found
                            if ((endTagIndex < 0) && (startTagIndex >= 0)) {
                                startTagIndex = -1;
                            }

                            // determine if in emphasize mode
                            boolean emphasize = (beginIndex == startTagIndex);

                            // skip over start tag and set stream to bold font style
                            if (emphasize) {
                                beginIndex += EMPHASIS_START_TAG.length();
                                stream.setFontStyle(SWT.BOLD);
                                endIndex = endTagIndex;
                            } else {
                                stream.setFontStyle(SWT.NORMAL);
                                endIndex = ((startTagIndex < 0) ? msgLength : startTagIndex);
                            }

                            // print to console
                            stream.print(message.substring(beginIndex, endIndex));
                            
                            // need to construct a new stream as changes to font style seem to only work one time
                            stream = newMessageStream();

                            // skip over end tag
                            if (emphasize) {
                                endIndex += EMPHASIS_END_TAG.length();
                            }

                            beginIndex = endIndex;
                        }

                        if (doLineFeedAtEnd) {
                            stream.println();
                        }
                    }
                }
            });
        }
    }

    // ===========================================================================================================================
    // Inner Class
    // ===========================================================================================================================

    /**
     * The <code>HyperlinkCreator</code> creates a hyperlink in a Message Console for the first occurrence of the full path of
     * a specified file.
     */
    class HyperlinkCreator implements IDocumentListener {

        // =======================================================================================================================
        // Fields
        // =======================================================================================================================

        /**
         * The console where the message is printed to and the hyperlink will be created.
         */
        private final PublishingMessageConsole console;

        /**
         * The file whose full path will become a hyperlink.
         */
        private final IFile file;

        /**
         * The message where the file path is located in.
         */
        private final String message;

        // =======================================================================================================================
        // Constructors
        // =======================================================================================================================

        /**
         * @param message the message that contains the full path of the file
         * @param console the console where the message appears
         * @param file the file whose full path appears in the message and will become a hyperlink
         */
        public HyperlinkCreator( String message,
                                 PublishingMessageConsole console,
                                 IFile file ) {
            this.message = message.replaceAll("<em>", "").replaceAll("\\Q</em>\\E", "");
            this.console = console;
            this.file = file;
        }

        // =======================================================================================================================
        // Methods
        // =======================================================================================================================

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.text.IDocumentListener#documentAboutToBeChanged(org.eclipse.jface.text.DocumentEvent)
         */
        @Override
        public void documentAboutToBeChanged( DocumentEvent arg0 ) {
            // nothing to do
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.text.IDocumentListener#documentChanged(org.eclipse.jface.text.DocumentEvent)
         */
        @Override
        public void documentChanged( DocumentEvent event ) {
            IDocument document = event.getDocument();

            try {
                FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
                IRegion region = finder.find(document.getLength() - 1, this.message, false, true, false, false);

                if (region != null) {
                    String target = this.file.getFullPath().toString();
                    int index = this.message.indexOf(target);

                    if (index == -1) {
                        throw new BadLocationException(RestClientI18n.publishingConsoleFilePathNotFoundMsg.text(target));
                    }

                    this.console.addHyperlink(new FileLink(file, null, -1, -1, -1), (region.getOffset() + index), target.length());

                    // created hyperlink so no need to listen any longer
                    document.removeDocumentListener(this);
                }
            } catch (BadLocationException e) {
                Activator.getDefault().log(new Status(Severity.ERROR,
                                                      RestClientI18n.publishingConsoleProblemCreatingHyperlinkMsg.text(), e));
                document.removeDocumentListener(this);
            }
        }
    }

}
