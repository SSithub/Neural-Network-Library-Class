package neuralnetwork;

import java.util.ArrayList;

public class XOR {

    public static void main(String[] args) {
        class Data {

            float[][] inputs;
            float[][] targets;

            Data(float[] inputs, float[] targets) {
                this.inputs = new float[][]{inputs};
                this.targets = new float[][]{targets};
            }
        }
        NNLib.NN nn = new NNLib().new NN(
                "xor",//Name for Saving & Graph Title
                7777,//Seed For Reproducibility
                .001,//Learning Rate for Optimizer
                NNLib.Initializer.VANILLA,//Weight Initializer Method
                NNLib.ActivationFunction.SIGMOID,//Hiddens
                NNLib.ActivationFunction.SIGMOID,//Outputs
                NNLib.LossFunction.QUADRATIC,//Loss/Cost/Error Function
                NNLib.Optimizer.ADAM,//Stochastic Gradient Descent Optimizer
                2, 2, 1//Network Architecture
        );

        nn.load();
        
        System.out.println(nn.NETWORKSIZE);
        System.out.println(nn.toString());
        ArrayList<Data> data = new ArrayList<>();
        data.add(new Data(new float[]{1, 1}, new float[]{0}));//True, True = False
        data.add(new Data(new float[]{0, 1}, new float[]{1}));//False, True = True
        data.add(new Data(new float[]{1, 0}, new float[]{1}));//True, False = True
        data.add(new Data(new float[]{0, 0}, new float[]{0}));//False, False = False
        NNLib.graph(false, nn);
        for (int i = 0; i < 10000000; i++) {
            int index = nn.getRandom().nextInt(4);
//            nn.print(data.get(index).inputs, "inputs");
//            nn.print(nn.feedforward(data.get(index).inputs), "feedforward");
//            System.out.println("");
            nn.backpropagation(data.get(index).inputs, data.get(index).targets);
            if(i % 10000 == 0){
                nn.save();
            }
        }
        System.exit(0);
    }
}
