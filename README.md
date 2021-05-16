# PackingFeasibility
Consider a logistics company delivering goods to customers. In order to save time and transportation cost, it is necessary to load the trucks in such a way that they can accommodate maximum possible goods into the trucks.

Packing feasibility will help us with best possible way to pack a truck with goods available to deliver.

Packing feasibility is written in java, and takes standard inputs of goods. We considered two dimesnional goods(length and breadth), as we assume them to be heavy and fragile and cannot be overlapped.

We caliculate the maximum area and minimum area occupied by all the goods which helps in understanding how much area might be required to load them properly.

The required area to load the goods will be in between maximum area and minimum area.

We try placing goods recursively and check for best possible loading.


inputs:
As we run the program, it asks for standard input of height and width.
in the format: "height,width" and click enter. 
once all the goods dimesnionals are given, click enter twice.

output:

A matrix is printed, where 0 denotes empty spaces and the numbers denote the goods.


for example,
i/p:

Give inputs and click enter twice:
2,4
1,2
3,3
4,4


o/p:

Packing Feasibility truck is:
 1  1  1  1 
 1  1  1  1 
 2  2  0  0 
 3  3  3  0 
 3  3  3  0 
 3  3  3  0 
 4  4  4  4 
 4  4  4  4 
 4  4  4  4 
 4  4  4  4 
 
 
