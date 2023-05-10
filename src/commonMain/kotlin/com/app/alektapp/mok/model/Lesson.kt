package com.app.alektapp.mok.model

import com.app.alektapp.domain.model.LessonBase
import com.app.alektapp.domain.model.LessonTittleBase
import com.app.alektapp.domain.model.LevelBase
import com.app.alektapp.domain.model.SoundUrl
import com.app.alektapp.mok.Mock

fun Mock.lessonBase(index: Int)  = LessonBase(
        header = LessonTittleBase(
            id = "someIdlessonBase$index",
            title = "mok title",
            subTitle = " mok sub title",
        ),
        text = "JACOB SOVER. Lone sidder i Paris i sin nye {lejlighed} . Hun bor her {sammen med} Jacob. De har boet her en måned nu. Hun ser på {uret} . Klokken er snart ti. Jacob er ikke stået op. Han kom sent hjem i går. Lone åbner døren ind til soveværelset. Jacob åbner øjnene. Han ser på Lone. Har du sovet godt? spørger Lone. Jacob svarer ikke. Det {blev sent} , siger Lone. Ja, de {sidste gæster gik halv et} .   RESTAURANTEN. Jacob arbejder på en fin, fransk restaurant. Her er han {kok} . Han arbejder {hårdt} . Hver dag lærer han nye {ting} . Han laver franske supper. Han laver {frølår} . Og han lærer alt om franske {oste} . Lavede du nye {retter}  i går? spørger Lone. Nej, men jeg {smagte en dyr vin} . Den {koster} over 2000 kr. Det er en Bordeax. Vil du {smage} ? Jeg har lidt med hjem. Nej, tak. siger Lone. Så går hun ind i stuen igen.  FRISØR. Jacob er glad for at være kok. Han skal lære at lave fransk mad. Det er {derfor} , de er i Paris. Men hvad med Lone? Hun er {frisør} . Hun havde et godt job i Danmark. Hun måtte {sige op}  og rejse med Jacob. Nu har hun ikke noget arbejde. Det er {svært} for Lone at få arbejde i Paris. Hun kan ikke fransk. Så kan hun ikke tale med {kunderne} . Er der nyt om arbejdet? spørger Jacob. Han sidder i stuen og spiser morgenmad. Nej, siger Lone. Hun ser ned i bordet. {Hun er ked af det} . Men hun er også {vred} . Hun er {arbejdsløs}  og synes, det er Jacobs {skyld}. Måske skal jeg rejse hjem, siger Lone. Nej, du skal ikke. Jeg {tjener}  godt, siger Jacob. Du kan gå ture i Paris. Du kan gå på café eller læse en bog. Jeg tjener {penge}  nok til to. Du forstår det ikke. Jeg vil ikke gå et år uden arbejde. Jeg er frisør. Jeg vil arbejde, siger Lone. Men jeg kan ikke fransk. Så lær fransk. Gå på kursus. Så kan du også få nogle gode venner, siger Jacob. ",
        soundUrl = SoundUrl(value = "https://mediafiles-alectapp-external.s3.amazonaws.com/Jacob.mp3"),
        level = LevelBase.A2
    )
