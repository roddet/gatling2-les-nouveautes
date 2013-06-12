import io.gatling.recorder.config.RecorderPropertiesBuilder
import io.gatling.recorder.controller.RecorderController

object Recorder extends App {

	val props = new RecorderPropertiesBuilder
	props.simulationOutputFolder(IDEPathHelper.recorderOutputDirectory.toString)
	props.simulationPackage("com.roddet.blog.gatling2.news")
	props.requestBodiesFolder(IDEPathHelper.requestBodiesDirectory.toString)

	RecorderController(props.build)
}