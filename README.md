<h1>What is this?</h1>
This is a to do list app written in Java using JavaFX and SQLite.

<h1>Why did I write this?</h1>
I wrote a CLI version of a very similar app to get familiar with Java and its syntax. This is a GUI version of the same app with virtually the same functionality.

<h1>Why are there two versions of this repo?</h1>
I started writing this app following the tutorial here:
https://www.pragmaticcoding.ca/beginners/intro
You can see that my app structure followed the structure in this tutorial very closely. As my app started to grow, I was finding it difficult to expand the functionality. Looking back at the code, there were a few culprits:
<h3>Issues with the first version</h2>
<ol>
  <li>Separation of concerns</li>
  <li>Circular dependency</li>
  <li>Over-reliance on specific typing</li>
  <li>No use of enums</li>
</ol>

A brief note about each point:
<h4>Separation of concerns</h4>
Looking back at my code, some classes are doing much more than they should. A good example is the ViewController class. It was handling updating displays, task logic, user input, and wiring. So much power was focused into this one class that it made it exceptionally difficult to share with other parts of the app. In my revised verison, ViewController now implements the ViewPort interface, whose sole job is to update displays. Keeping the class single responsibility limited the effect of changes to other parts of the system and made it easier to expand the app.
<h4>Circular dependency</h4>
A side effect of the above separation of concerns complaint is that everyone started depending on everyone else, namely ViewController. The straw that broke the camel's back was trying to edit the ScenePack class that contained all the elements of the layout, and realizing that the ViewController needed the ScenePack but the ScenePack needed the ViewController. This was where I really felt that things had gone awry because I changed handful of things in the codebase and immediately the whole thing wouldn't compile. I tried to disentangle the classes but realized that I had made a bit of a mess of it, and decided to start over. In the second run through, I focused on making depenedency go 'down-stream', such that the dependency chain only flows one way and things that are required to 'skip' a link in the chain e.g. the ViewController needing displays from the LayoutManager, are handled through dependency injection, with almost everything being instantiated in Main.
<h4>Over-reliance on specific typing</h4>
I didn't use any interfaces in my initial draft of the project. I thought that interfaces were too confusing and couldn't see why I would need to use one if I could call specific methods from another class. Until I changed something. Then I realized that I'd need to go back throughout the project and try to change everything to match the new setup. It just wasn't working. I started using interfaces for the 'big' powerful classes that would be pulling a lot of weight and might need some overhauls in the future. This turned out to be a good move because I decided to migrate from an in-memory database to an SQLite database, with the idea that I could try to make a web app version of the app later and sync them up. When I changed the classes over from the in-memory task controller and service to the SQLite version, it was much easier when the 'contract' between the classes had already been established. It was a matter of just plugging in the right pieces.
<h4>No use of enums</h4>
This isn't a huge issue, in my opinion, but it did improve the code clarity a lot. Initially, I was using specific methods to do specific things, which caused a lot of code duplication and was honestly really messy. I changed over to using enums in maps to get specific elemnents of the UI, mostly. This made my methods reusable, cleaner, and abstracted a lot of the details into a more 'human' way of thinking about the code. I may have even overused enums in the second version - oops.

<h1>Use of AI</h1>
I made use of ChatGPT in the creation of this project. I used ChatGPT for the following:
<ol>
  <li>'Talking' through the high level structure of the app</li>
  <li>Pointing me in the direction of methods and concepts that might help with a given task</li>
  <li>Explaining Java functionality like enums and interfaces when I wasn't understanding the documentation</li>
  <li>Providing warm up exercises to start my coding sessions i.e. using streams, filter, forEach, optionals etc</li>
</ol>
One thing that I think I learned from this way of using AI is that AI can be very helpful in rubber ducking your thought process and can remove a lot of the difficulty from finding methods and ways of tackling a problem that would otherwise take a long time of sifting through unrelated material in the documentation.
<p>I made a point of asking ChatGPT to refrain from giving code examples and to keep things conceptual. This let me focus on the implementation myself. I'm happy I did it this way because I was able to implement the system in a way that I understand and built a lot of muscle memory with the Java syntax. At the start of the project, I found myself soundboarding my ideas off of ChatGPt a lot. By the end of the project, I preferred to just open my IDE and get stuck in by myself. I think the shift came from increased confidence and understanding of how I built the project up to that point. If I were to implement a similar CRUD app, I don't think I would consult ChatGPT nearly as much as I already have my own mental model of how this kind of project can be structured. I feel like AI is a great learning tool to get up to pace quickly when used in this way and the end goal is to reduce your use of it over time.</p>
