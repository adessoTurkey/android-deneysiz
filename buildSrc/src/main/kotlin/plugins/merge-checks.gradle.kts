package plugins

import org.codehaus.groovy.runtime.ProcessGroovyMethods.execute
import org.codehaus.groovy.runtime.ProcessGroovyMethods.getText

abstract class MergeChecksTask : DefaultTask() {

    @TaskAction
    fun check() {
        val targetBranch = "develop"
        val commitCount = numbOfCommitBetweenSourceAndHead(targetBranch)

        if (commitCount > 1) {
            throw GradleException(
                "Source branch is out of date with $targetBranch branch " +
                    "and is ahead of it by $commitCount commits.\n" +
                    "Rebase the source branch and squash all commits into one before pushing."
            )
        } else {
            println(
                "Source branch and is ahead of $targetBranch by " +
                    "$commitCount commits and it can be merged."
            )
        }
    }

    private fun numbOfCommitBetweenSourceAndHead(source: String): Int {
        val commands = arrayListOf(
            "sh",
            "-c",
            "git rev-list --count $source..HEAD"
        )

        val process = execute(commands).also {
            it.waitFor()
            if (it.exitValue() != 0) throw  RuntimeException(getText(it))
        }
        return getText(process).trim().toInt()
    }
}
