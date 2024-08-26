package com.memozi.domain.usecase

import com.memozi.domain.model.Memozi
import com.memozi.domain.repository.MemoziRepository
import javax.inject.Inject

class PostMemoziUseCase @Inject constructor(
    private val memoziRepository: MemoziRepository,
) {
    suspend operator fun invoke(memozi: Memozi) = memoziRepository.postMemozi(memozi)
}
