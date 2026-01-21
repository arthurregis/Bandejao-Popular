package com.example.restaurantepopular.ui.componets.pdfgenerator

import android.content.Context
import android.util.Log
import java.io.File

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter

import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph

import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.io.font.constants.StandardFonts

class PdfGenerator(private val context: Context) {

    val boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)

    fun gerarRelatorio(
        consumo: List<Map<String, Any>>,
        desperdicios: List<Map<String, Any>>,
        naoAtendidos: List<Map<String, Any>>
    ): File {

        Log.d("PDF", "Iniciando geração do PDF")

        val dir = context.getExternalFilesDir(null)
        Log.d("PDF", "Diretório: ${dir?.absolutePath}")

        val file = File(
            context.getExternalFilesDir(null),
            "relatorio_${System.currentTimeMillis()}.pdf"
        )

        Log.d("PDF", "Arquivo será criado em: ${file.absolutePath}")

        val writer = PdfWriter(file)
        val pdf = PdfDocument(writer)
        val document = Document(pdf)

        document.add(Paragraph("RELATÓRIO RESTAURANTE POPULAR")
            .setFont(boldFont)
            .setFontSize(18f))

        fun addSection(title: String, dados: List<Map<String, Any>>) {
            document.add(Paragraph(title).setFont(boldFont).setFontSize(14f))

            dados.forEach { item ->
                item.forEach { (k, v) ->
                    document.add(Paragraph("$k: $v"))
                }
                document.add(Paragraph("------------"))
            }
        }

        if (consumo.isNotEmpty()) addSection("CONSUMO", consumo)
        if (desperdicios.isNotEmpty()) addSection("DESPERDÍCIOS", desperdicios)
        if (naoAtendidos.isNotEmpty()) addSection("NÃO ATENDIDOS", naoAtendidos)

        document.close()

        Log.d("PDF", "PDF finalizado")
        return file
    }
}