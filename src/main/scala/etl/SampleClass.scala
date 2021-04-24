package etl


class SampleClass(aggMethodStr: String = "gma") extends Serializable with ExtendableTrait {

    def sampleMethod(arg1: String): String = {
        print("cool")
        "done!"
    }

}