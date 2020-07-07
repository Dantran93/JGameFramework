import java.util.Stack;

/**
 * JGameSceneManager
 *
 * Purpose: Provides an interface to manage game scenes.
 *      The scenes are managed in a Stack structure.
 */
public final class JGameSceneManager
{
    private static Stack<JGameScene> scenes = new Stack<>();


    /**
     * getActiveScene ()
     *
     * Purpose: Returns the active scene.
     */
    public static JGameScene getActiveScene ()
    {
        return scenes.peek();
    } // getActiveScene ()


    /**
     * getPreviousScene ()
     *
     * Purpose: Returns the previous scene relative to the active scene.
     */
    public static JGameScene getPreviousScene ()
    {
        return scenes.get(scenes.size()-2);
    } // getPreviousScene ()


    /**
     * addScene (JGameScene)
     *
     * Purpose: Pushes a new scene to the stack of scenes.
     */
    public static void addScene (final JGameScene newScene)
    {
        scenes.add(newScene);
    } // addScene (JGameScene)


    /**
     * removeScene ()
     *
     * Purpose: Removes the active scene from the stack of scenes.
     */
    public static void removeScene ()
    {
        scenes.pop();
    } // removeScene ()

} // final class JGameSceneManager
