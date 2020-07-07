/**
 * JGameWindowSettings
 *
 * Purpose: Contains the settings for a JGameEngine window.
 */
public final class JGameWindowSettings
{
    private static final double DEFAULT_WINDOW_SIZE = 500.0;

    private String windowTitle;
    private double windowWidth;
    private double windowHeight;
    private boolean hasWindowDecorations;


    /**
     * JGameWindowSettings ()
     *
     * Purpose: Creates and initializes default JGameWindowSettings.
     */
    public JGameWindowSettings ()
    {
        this.windowTitle = "JGame";
        this.windowWidth = DEFAULT_WINDOW_SIZE;
        this.windowHeight = DEFAULT_WINDOW_SIZE;
        this.hasWindowDecorations = true;
    } // JGameWindowSettings ()


    /**
     * JGameWindowSettings (String)
     *
     * Purpose: Creates and initializes JGameWindowSettings with the given window title.
     */
    public JGameWindowSettings (final String windowTitle)
    {
        this();
        this.windowTitle = windowTitle;
    } // JGameWindowSettings (String)


    /**
     * JGameWindowSettings (String, double, double)
     *
     * Purpose: Creates and initializes JGameWindowSettings with the given window title, width, and height.
     */
    public JGameWindowSettings (final String windowTitle, final double windowWidth, final double windowHeight)
    {
        this(windowTitle);
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    } // JGameWindowSettings (String, double, double)


    /**
     * JGameWindowSettings (String, double, double, boolean)
     *
     * Purpose: Creates and initializes JGameWindowSettings with the given window title, width, height,
     *      and decorations indicator.
     */
    public JGameWindowSettings (final String windowTitle, final double windowWidth, final double windowHeight,
                                final boolean hasWindowDecorations)
    {
        this(windowTitle, windowWidth, windowHeight);
        this.hasWindowDecorations = hasWindowDecorations;
    } // JGameWindowSettings (String, double, double, boolean)


    /**
     * getWindowTitle ()
     *
     * Purpose: Returns the window title setting.
     */
    public String getWindowTitle ()
    {
        return this.windowTitle;
    } // getWindowTitle ()


    /**
     * getWindowWidth ()
     *
     * Purpose: Returns the window width setting.
     */
    public double getWindowWidth ()
    {
        return this.windowWidth;
    } // getWindowWidth ()


    /**
     * getWindowHeight ()
     *
     * Purpose: Returns the window height setting.
     */
    public double getWindowHeight ()
    {
        return this.windowHeight;
    } // getWindowHeight ()


    /**
     * hasWindowDecorations ()
     *
     * Purpose: Returns the status of the flag that sets the window decorations.
     *      true -- Window decorations are ON.
     *      false -- Window decorations are OFF.
     */
    public boolean hasWindowDecorations ()
    {
        return this.hasWindowDecorations;
    } // hasWindowDecorations ()

} // final class JGameWindowSettings
