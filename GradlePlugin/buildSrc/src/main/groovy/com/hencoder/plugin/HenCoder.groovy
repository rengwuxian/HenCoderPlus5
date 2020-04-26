package com.hencoder.plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class HenCoder implements Plugin<Project> {
  @Override
  void apply(Project target) {
    def extension = target.extensions.create('hencoder', HenCoderExtension)
    target.afterEvaluate {
      println "Hi ${extension.name}!"
    }
    def transform = new HenCoderTransform()
    def baseExtension = target.extensions.getByType(BaseExtension)
    baseExtension.registerTransform(transform)
  }
}